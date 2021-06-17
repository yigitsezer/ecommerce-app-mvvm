package com.yigitsezer.ecommerceapp.repository.model

import com.google.gson.annotations.SerializedName

class Category(
    @SerializedName("id")
    var id: Int,

    @SerializedName("campaignName")
    var campaignName: String,

    @SerializedName("imageUrl")
    var imageUrl: String,

    @SerializedName("category")
    var category: String
)
