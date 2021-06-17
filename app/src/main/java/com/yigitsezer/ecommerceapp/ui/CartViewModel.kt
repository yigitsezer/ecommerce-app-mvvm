package com.yigitsezer.ecommerceapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel: ViewModel() {
    private val _isCartEmpty: MutableLiveData<Boolean> = MutableLiveData(true)
    val isCartEmpty: LiveData<Boolean> get() = _isCartEmpty

    fun addToCart() {
        _isCartEmpty.value = false
    }
}