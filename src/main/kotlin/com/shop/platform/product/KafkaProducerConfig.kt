package com.shop.platform.product

import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.*
import java.util.*

@Configuration
class KafkaProducerConfig {

    @Autowired
    private var kafkaProperties: KafkaProperties? = null

    @Bean
    fun producerConfigs(): Map<String, Any>? {
        val props: MutableMap<String, Any> = HashMap(kafkaProperties!!.buildProducerProperties())
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringSerializer"
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = "org.springframework.kafka.support.serializer.JsonSerializer"
        return props
    }

    @Bean
    fun producerFactory(): ProducerFactory<String, Any>{
        return DefaultKafkaProducerFactory(producerConfigs()!!)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Any>? {
        return KafkaTemplate(producerFactory())
    }
}