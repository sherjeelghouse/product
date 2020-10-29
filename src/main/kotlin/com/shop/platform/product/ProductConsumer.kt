package com.shop.platform.product

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ProductConsumer {

    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["product-delete"], containerFactory = "kafkaListenerContainerFactory")
    fun consumeMessage(message: JsonNode) {
        val product = ObjectMapper().convertValue(message, Product::class.java)
        logger.info("Message received: ${product.toString()}")
    }
}
