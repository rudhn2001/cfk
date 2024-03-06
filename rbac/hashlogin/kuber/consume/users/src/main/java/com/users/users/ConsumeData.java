package com.users.users;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
public class ConsumeData {

public void ConsumeData() {
        String bootstrapServer = "pkc-4r087.us-west2.gcp.confluent.cloud:9092";
        String localhostServer = "localhost:9092";
        String topic = "userdata";

        // Set your Confluent Cloud API Key and Secret
        String apiKey = "AAVGR5QOLGCB23ES";
        String apiSecret = "R5iUExEeAwxp2CWMqocoakf7OAqqIXfZmuyQTHx1Xh121abZuoAR7brIQpxX2CtL";

        TopicPartition topicPartition = new TopicPartition(topic, 0);
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group_310");
        properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG,"11000");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,"20000");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Set to 'earliest' to consume from the beginning
//    TopicPartition topicPartition = new TopicPartition(topic,0);


//        properties.put("metadata.fetch.timeout.ms", "30000");
//    properties.put(ConsumerConfig.SOCKET_CONNECTION_SETUP_TIMEOUT_MAX_MS_CONFIG, "30000");
//    properties.put("max.block.ms", "30000");
        properties.put("security.protocol", "SASL_SSL");
        properties.put("sasl.mechanism", "PLAIN");
        properties.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"" + apiKey + "\" password=\"" + apiSecret + "\";");

        // Create Kafka consumer
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList(topic));
//        consumer.seek(topicPartition,0);

        try {
            // Poll for messages
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
                consumer.commitSync();
                Thread.sleep(1000);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
}
