package com.yigitsezer.ecommerceapp.ui.category_viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.yigitsezer.ecommerceapp.databinding.FragmentCategorySlideBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment(var categoryImageUrl: String): Fragment() {
    private var _binding: FragmentCategorySlideBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategorySlideBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(categoryImageUrl).into(binding.ivCategoryImage)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}