package com.shop.platform.product

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(@Id
                   @GeneratedValue(strategy = GenerationType.IDENTITY)
                   @JsonProperty("id")
                   val id: Long?,
                   @Column(name="name")
                   @JsonProperty("name")
                   val name: String?,
                   @Column(name="desc")
                   @JsonProperty("desc")
                   val desc: String?)