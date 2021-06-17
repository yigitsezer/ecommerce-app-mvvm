package com.yigitsezer.ecommerceapp.ui.category_viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yigitsezer.ecommerceapp.repository.model.Category

class CategoryPagerAdapter(var categories: List<Category>, var fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment {
        return CategoryFragment(categories[position].imageUrl)
    }
}