package com.shop.platform.product

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.doReturn

class ProductControllerUnitTest {

    @Test
    fun testProductController() {
        val productRepository = Mockito.mock(ProductRepository::class.java)
        val productProducer = Mockito.mock(ProductProducer::class.java)

        val expected = Product(1, "Frito Lay", "Chips")
        doReturn(expected).`when`(productRepository).getOne(1)

        val productController = ProductController(productRepository, productProducer)

        val result = productController.getProduct(1)
        Mockito.verify(productRepository).getOne( 1)
        assertNotNull(result)
        assertEquals(expected, result)
    }
}