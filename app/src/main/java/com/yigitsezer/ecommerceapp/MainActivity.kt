package com.yigitsezer.ecommerceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.yigitsezer.ecommerceapp.databinding.ActivityMainBinding
import com.yigitsezer.ecommerceapp.ui.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onStart() {
        super.onStart()

        cartViewModel.isCartEmpty.observe(this, {
            if (it) binding.headerTabBar.ivCart
                .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_cart))
            else binding.headerTabBar.ivCart
                .setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_cart_active))
        })

        binding.headerTabBar.btnBack.setOnClickListener {
            findNavController(R.id.nav_host_fragment).popBackStack()
        }
    }
}