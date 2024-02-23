package com.users.users;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;

// import java.util.random.*;
import java.util.Properties;
import java.util.Random;

public class AddJsonData {

    // BOOTSTRAP SERVERS

    String bootstrapServers = "pkc-4r087.us-west2.gcp.confluent.cloud:9092";
    String localhostServer = "localhost:9092";
    String KubernetesServer0 = "kafka-0.kafka.mydomain.example:9092";
    String KubernetesServer1 = "kafka-0.kafka.confluent.svc.cluster.local:9092";

    // API KEYS FOR ONLINE CLOUD
    // String apiKey = "LRUVLJHFTRQNI3B7";
    // String apiSecret = "yOKpzZZXcheVBkaphcRGh9kqLrLgGG8qHkmLxllI33Cl2r8Jwsb//D3/0dfhgBaZ";

    String username="bob";
    String password="bob";


    int i = 0;
    int flag = 0;

    // TOPICS

    String topic = "userdata";
    String KubernetesTopic = "cfktopic1";

    // Method to produce JSON data

    public void produceJsonData() {
        Random rand = new Random();
        int key1 = rand.nextInt(100);
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KubernetesServer0);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "PLAIN");
        props.put("sasl.jaas.config",
        "org.apache.kafka.common.security.plain.PlainLoginModule required username=\""
        + username + "\" password=\"" + password + "\";");

        Producer<String, String> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);

        while (i < 10) {

            try {
                GenerateData generateData = new GenerateData();
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonString = objectMapper.writeValueAsString(generateData);

                if (flag == 0) {
                    String key = Integer.toString(key1);
                    ProducerRecord<String, String> record = new ProducerRecord<>(KubernetesTopic, key, jsonString);
                    producer.send(record);
                    key1++;
                    flag = 1;
                }

                if (flag == 1) {
                    System.out.println("JSON message sent successfully to topic: " + topic);
                    System.out.println("Values sent  : ");
                    System.out.println("ID : " + key + "\n First Name : " + generateData.fname + "\n Last Name : "
                            + generateData.lname + "\n Address : " + generateData.address +
                            "\n Email : " + generateData.email + "\n Contact : " + generateData.contact);

                    Thread.sleep(5000);
                    flag = 0;
                }

                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
            // finally {
            // producer.close();
            // }
        }

    }
}
