package com.shop.platform.product

import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.connect.json.JsonDeserializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
class KafkaConsumerConfig {

    @Autowired
    private var kafkaProperties: KafkaProperties? = null

    @Bean
    fun consumerFactory(): ConsumerFactory<String?, JsonNode?> {
        return DefaultKafkaConsumerFactory(
                kafkaProperties!!.buildConsumerProperties(), StringDeserializer(), JsonDeserializer()
        )
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, JsonNode>? {
        val factory = ConcurrentKafkaListenerContainerFactory<String, JsonNode>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}