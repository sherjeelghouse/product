package com.shop.platform.product

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController @RequestMapping("/api/v1/product") class ProductController(private val repository: ProductRepository, private val producer: ProductProducer) {
    @PostMapping
    fun addProduct(@RequestBody product: Product): ResponseEntity<Product> =
            ResponseEntity.ok(repository.save(product))

    @GetMapping("/{id}")
    fun getProduct(@PathVariable("id") id: Long): Product = repository.getOne(id)

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable(value = "id") id: Long, @RequestBody product: Product): ResponseEntity<Product> =
            repository.findById(id).map { existingProduct ->
                val updatedProduct: Product = existingProduct.copy(name = product.name, desc = product.desc)
                ResponseEntity.ok().body(repository.save(updatedProduct))
            }.orElse(ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable("id") id: Long) {
        val product = getProduct(id);
        repository.deleteById(id)
        producer.send(product)
    }
}