package com.yigitsezer.ecommerceapp.ui.product

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.yigitsezer.ecommerceapp.R
import com.yigitsezer.ecommerceapp.databinding.FragmentProductBinding
import com.yigitsezer.ecommerceapp.repository.model.Product
import com.yigitsezer.ecommerceapp.ui.CartViewModel
import com.yigitsezer.ecommerceapp.ui.category_viewpager.CategoryPagerAdapter
import com.yigitsezer.ecommerceapp.ui.product_viewpager.ProductImagePagerAdapter
import com.yigitsezer.ecommerceapp.util.autoScroll
import com.yigitsezer.ecommerceapp.util.setActive
import com.yigitsezer.ecommerceapp.util.setPicked
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment: Fragment() {
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val sharedProductViewModel: SharedProductViewModel by activityViewModels()
    private val cartViewModel: CartViewModel by activityViewModels()

    private var amount: Int = 1
    private var selectedPosition: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedProductViewModel.product.observe(viewLifecycleOwner, { product ->
            binding.vpDetailProductImages.adapter = ProductImagePagerAdapter(product.images.filterNotNull(), this)


            binding.tvDetailProductName.text = product.productName //brandName?
            binding.tvDetailDesc.text = HtmlCompat.fromHtml(product.productDetailInfo.orEmpty(), HtmlCompat.FROM_HTML_MODE_COMPACT) //add html

            product.priceDiscount?.let {
                binding.tvDetailPrice.text = "${product.priceDiscount}₺"
                binding.tvDetailOldPrice.apply {
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    text = "${product.price}₺"
                }
            }?: run {
                binding.tvDetailOldPrice.visibility = View.GONE
                binding.tvDetailPrice.text = "${product.price}₺"
            }

            binding.tvDetailAmountPicker.tvAmount.text = "$amount"
            binding.tvDetailAmountPicker.btnAmountDecrease.setOnClickListener {
                if (amount != 1) amount--
                binding.tvDetailAmountPicker.tvAmount.text = "$amount"
            }
            binding.tvDetailAmountPicker.btnAmountIncrease.setOnClickListener {
                if (amount != 10) amount++
                binding.tvDetailAmountPicker.tvAmount.text = "$amount"
            }

            setupSizesRecyclerView(product)

            binding.btnWhereLabel.setOnClickListener { 
                findNavController().navigate(R.id.mapFragment)
            }
            //navigate to map fragment

        })
    }

    private fun setupSizesRecyclerView(product: Product) {
        binding.rvSizes.layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        binding.rvSizes.adapter = SizePickersAdapter(product.sizes, selectedPosition, object: OnSizeClickListener {
            override fun onClick(position: Int) {
                if (selectedPosition == position) {
                    selectedPosition = -1
                    binding.btnAddToCart.setActive(false)
                    binding.btnAddToCart.setOnClickListener { }
                } else {
                    selectedPosition = position
                    binding.btnAddToCart.setActive(true)
                    binding.btnAddToCart.setOnClickListener {
                        cartViewModel.addToCart()
                        if (findNavController().currentDestination?.id == R.id.productDetailFragment)
                            findNavController().navigate(R.id.successDialogFragment)
                    }
                }
                setupSizesRecyclerView(product)
            }
        })
        binding.rvSizes.adapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}