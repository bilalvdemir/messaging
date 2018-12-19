package com.netas.message.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.stereotype.Service;

import com.netas.message.bus.config.SingletonTopic;
import com.netas.message.bus.producer.MessageProducerService;
import com.netas.message.model.User;
import com.netas.message.repository.UserRepository;

import ch.qos.logback.classic.net.SyslogAppender;

@Service
public class UserService {

    @Autowired
    MessageProducerService        messageProducerService;

    @Autowired
    KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @Autowired
    private UserRepository        userRepository;

    public User findById(String name) {
        return userRepository.findByUsername(name);
    }

    public User findByName(String name) {
        return userRepository.findByUsername(name);
    }

    public User findByNameAndPassword(String name, String password) {
        return userRepository.findByUsernameAndPassword(name, password);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void updateUser(User user) {
        saveUser(user);
    }

    public void deleteUserById(String username) {
        userRepository.deleteByUsername(username);
    }

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @SuppressWarnings("rawtypes")
    public void changeTopic(String topic, String group) {
        Map<String, Object> consumerConfig = new HashMap<>();
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, group);
   
        DefaultKafkaConsumerFactory<String, String> kafkaConsumerFactory = new DefaultKafkaConsumerFactory<>(
                consumerConfig,
                new StringDeserializer(),
                new StringDeserializer());

        ContainerProperties containerProperties = new ContainerProperties(topic);
        containerProperties.setMessageListener(
                (MessageListener<String, String>) record -> {
                    System.out.println(record.value());
                    });

        ConcurrentMessageListenerContainer container = new ConcurrentMessageListenerContainer<>(
                kafkaConsumerFactory,
                containerProperties);

        container.start();

//        kafkaListenerEndpointRegistry.getListenerContainers().forEach(listener -> listener.stop());
//        kafkaListenerEndpointRegistry.getListenerContainers().forEach(listener -> listener.start());

    }

    public void deleteAllUsers() {

        userRepository.deleteAll();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
