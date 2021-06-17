package com.yigitsezer.ecommerceapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.yigitsezer.ecommerceapp.R
import com.yigitsezer.ecommerceapp.databinding.FragmentMainBinding
import com.yigitsezer.ecommerceapp.ui.category_viewpager.CategoryPagerAdapter
import com.yigitsezer.ecommerceapp.ui.product.SharedProductViewModel
import com.yigitsezer.ecommerceapp.util.autoScroll
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainPageViewModel: MainPageViewModel by viewModels()
    private val sharedProductViewModel: SharedProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainPageViewModel.getMainPage()
        binding.vpCategories.autoScroll(lifecycleScope = viewLifecycleOwner.lifecycleScope, interval = 5000)
        mainPageViewModel.mainPage.observe(viewLifecycleOwner, {
            binding.vpCategories.adapter = CategoryPagerAdapter(it.categoryList, this)

            binding.tvHypeTitle.text = it.mainTitle
            binding.tvExploreTitle.text = it.subTitle

            binding.rvProducts.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            binding.rvProducts.adapter = ProductListAdapter(it.productList, object:OnProductClickListener {
                override fun onClick(position: Int) {
                    sharedProductViewModel.getProduct(position+1)
                    findNavController().navigate(R.id.productDetailFragment)
                }
            })
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}