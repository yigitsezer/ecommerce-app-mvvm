package com.yigitsezer.ecommerceapp.ui.main

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.yigitsezer.ecommerceapp.repository.StoreApiService
import com.yigitsezer.ecommerceapp.repository.model.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private var storeApiService: StoreApiService
): ViewModel() {
    val mainPage: MutableLiveData<Page> = MutableLiveData()

    fun getMainPage() {
        storeApiService.getMainPage().enqueue(object: Callback<Page> {
            override fun onResponse(call: Call<Page>, response: Response<Page>) {
                mainPage.value = response.body()
            }

            override fun onFailure(call: Call<Page>, t: Throwable) {

            }
        })
    }

}