package com.shop.platform.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductApplicationTests(@Autowired val restTemplate: TestRestTemplate) {

	@Test
	fun `Assert Product is returned`() {
		println(">> Assert that product is returned")
		val entity = restTemplate.getForEntity<String>("/api/v1/product/1", Product::class.java)
		assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
		assertThat(entity.body).contains("{\"id\":1,\"name\":\"Frito Lay\",\"desc\":\"Chips\"}")
	}
}