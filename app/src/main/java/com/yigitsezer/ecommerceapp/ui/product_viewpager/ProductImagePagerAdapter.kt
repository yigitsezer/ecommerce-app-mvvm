package com.yigitsezer.ecommerceapp.ui.product_viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProductImagePagerAdapter(var imageUrls: List<String>, var fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = imageUrls.size

    override fun createFragment(position: Int): Fragment {
        return ProductImageFragment(imageUrls[position])
    }
}