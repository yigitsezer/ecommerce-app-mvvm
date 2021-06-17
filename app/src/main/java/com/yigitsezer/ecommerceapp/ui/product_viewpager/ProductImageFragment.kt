package com.yigitsezer.ecommerceapp.ui.product_viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.yigitsezer.ecommerceapp.databinding.FragmentImageSlideBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductImageFragment(var imageUrl: String) : Fragment() {
    private var _binding: FragmentImageSlideBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageSlideBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(imageUrl).into(binding.ivProductDetailImage)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}