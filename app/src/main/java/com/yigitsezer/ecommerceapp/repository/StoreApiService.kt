package com.yigitsezer.ecommerceapp.repository

import com.yigitsezer.ecommerceapp.repository.model.Page
import com.yigitsezer.ecommerceapp.repository.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StoreApiService {
    @GET("main_page.php")
    fun getMainPage(): Call<Page>

    @GET("product_detail.php")
    fun getProduct(@Query("productId") id: Int): Call<Product>
}