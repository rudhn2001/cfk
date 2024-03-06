package com.users.users;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumeJsonData {
    public void ConsumeData() {

        // BOOTSTRAP SERVERS

        String bootstrapServers = "pkc-4r087.us-west2.gcp.confluent.cloud:9092";
        String localhostServer = "localhost:9092";
        String KubernetesServer3 = "kafka-0.kafka.confluent.svc.cluster.local:9092";
        String KubernetesServer1 = "kafka-0.kafka.confluent.svc.cluster.local:9071";

        // API KEYS FOR ONLINE CLOUD
        // String apiKey = "BWUEWQS7TDI24WUM";
        // String apiSecret = "I6xmivXBhtkMRvjr3jIMEhM2Hs+11j9ktbweKjK4owItY7vRieGEKTpmgHcWwY7y";

        String username = "charlie";
        String password = "charlie";



        // TOPICS

        String topic = "userdata";
        String KubernetesTopic = "cfktopic1";

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KubernetesServer1);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "example");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Set to 'earliest' to consume from the
                                                                             // beginning

        properties.put("security.protocol", "SASL_PLAINTEXT");
        properties.put("sasl.mechanism", "PLAIN");
        properties.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"" + username
                        + "\" password=\"" + password + "\";");

        // Create Kafka consumer
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList(KubernetesTopic));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                // Process the records
                records.forEach(record -> {
                    System.out.println("Received message:");
                    System.out.println("Key: " + record.key());
                    System.out.println("Value: " + record.value());
                    System.out.println("Partition: " + record.partition());
                    System.out.println("Offset: " + record.offset());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
