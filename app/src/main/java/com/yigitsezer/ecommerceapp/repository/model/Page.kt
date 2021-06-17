package com.yigitsezer.ecommerceapp.repository.model

import com.google.gson.annotations.SerializedName

class Page (
    @SerializedName("mainTitle")
    var mainTitle: String,

    @SerializedName("subTitle")
    var subTitle: String,

    @SerializedName("categoryList")
    var categoryList: List<Category>,

    @SerializedName("productList")
    var productList: List<ProductSmall>
)