package com.yigitsezer.ecommerceapp.repository.model

import com.google.gson.annotations.SerializedName

class Product (
    @SerializedName("id")
    var id: Int,

    @SerializedName("brandName")
    var brandName: String,

    @SerializedName("productName")
    var productName: String,

    @SerializedName("productDetailInfo")
    var productDetailInfo: String?,

    @SerializedName("category")
    var category: String,

    @SerializedName("sizes")
    var sizes: List<String>,

    @SerializedName("price")
    var price: String,

    @SerializedName("priceDiscount")
    var priceDiscount: String?,

    @SerializedName("latitude")
    var latitude: String,

    @SerializedName("longitude")
    var longitude: String,

    @SerializedName("images")
    var images: List<String?>

)