package com.yigitsezer.ecommerceapp.repository.model

import com.google.gson.annotations.SerializedName

class ProductSmall (
    @SerializedName("id")
    var id: Int,

    @SerializedName("brandName")
    var brandName: String,

    @SerializedName("productName")
    var productName: String,

    @SerializedName("category")
    var category: String,

    @SerializedName("price")
    var price: String,

    @SerializedName("priceDiscount")
    var priceDiscount: String?,

    @SerializedName("image")
    var image: String,
)
