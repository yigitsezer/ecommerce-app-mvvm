package com.yigitsezer.ecommerceapp.ui.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yigitsezer.ecommerceapp.repository.StoreApiService
import com.yigitsezer.ecommerceapp.repository.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SharedProductViewModel @Inject constructor(
    private var storeApiService: StoreApiService
): ViewModel() {
    val product: MutableLiveData<Product> = MutableLiveData()

    fun getProduct(id: Int) {
        storeApiService.getProduct(id).enqueue(object: Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                product.value = response.body()
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {

            }
        })
    }
}