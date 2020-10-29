package com.shop.platform.product

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ProductProducer(private val kafkaTemplate: KafkaTemplate<String, Any>) {

    @Value(value = "\${kafka.topic}")
    private val topic: String? = null

    fun send(message: Product) {
        if (topic != null) {
            kafkaTemplate.send(topic, (message).desc.toString(), message)
        }
    }
}

