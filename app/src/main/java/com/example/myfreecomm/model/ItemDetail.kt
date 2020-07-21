package com.example.myfreecomm.model

import java.io.Serializable

data class ItemDetail(
    val name: String,
    val quantity: Int,
    val stock: Int,
    val image_url: String,
    val price: Int,
    val tax: Int,
    val shipping: Int,
    val description: String
): Serializable