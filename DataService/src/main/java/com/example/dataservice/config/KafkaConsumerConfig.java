package com.example.dataservice.config;

import com.example.dataservice.dto.CommentDTO;
import com.example.dataservice.dto.NewsDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Value("${spring.kafka.consumer.group-id}")
    private String kafkaGroupId;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        return props;
    }

//    @Bean
    public ConsumerFactory<Integer, NewsDTO> consumerFactoryNews() {
        JsonDeserializer<NewsDTO> jsonDeserializer = new JsonDeserializer<>(NewsDTO.class);
        Jackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.addTrustedPackages("com.example.apiservice.dto", "com.example.dataservice.dto");
        jsonDeserializer.setTypeMapper(typeMapper);
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new IntegerDeserializer(), jsonDeserializer);
    }

    @Bean("kafkaListenerContainerFactoryNews")
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactoryNews() {
        ConcurrentKafkaListenerContainerFactory<Integer, NewsDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryNews());
        return factory;
    }

    //    @Bean
    public ConsumerFactory<Integer, CommentDTO> consumerFactoryComment() {
        JsonDeserializer<CommentDTO> jsonDeserializer = new JsonDeserializer<>(CommentDTO.class);
        Jackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.addTrustedPackages("com.example.apiservice.dto", "com.example.dataservice.dto");
        jsonDeserializer.setTypeMapper(typeMapper);
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new IntegerDeserializer(), jsonDeserializer);
    }

    @Bean("kafkaListenerContainerFactoryComment")
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactoryComment() {
        ConcurrentKafkaListenerContainerFactory<Integer, CommentDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryComment());
        return factory;
    }
}