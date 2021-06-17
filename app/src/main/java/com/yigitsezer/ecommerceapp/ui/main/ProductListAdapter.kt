package com.yigitsezer.ecommerceapp.ui.main

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yigitsezer.ecommerceapp.R
import com.yigitsezer.ecommerceapp.repository.model.ProductSmall
import com.yigitsezer.ecommerceapp.util.ProductType
import com.yigitsezer.ecommerceapp.util.setProductType

class ProductListAdapter(private var productList: List<ProductSmall>, var onProductClickListener: OnProductClickListener): RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {
    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val productImageImageView: ImageView = view.findViewById(R.id.iv_product_image)
        val productNameTextView: TextView = view.findViewById(R.id.tv_product_name)
        val productTypeTextView: TextView = view.findViewById(R.id.tv_product_type)
        val productOldPriceTextView: TextView = view.findViewById(R.id.tv_old_price)
        val productPriceTextView: TextView = view.findViewById(R.id.tv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProductSmall: ProductSmall = productList[position]
        Picasso.get().load(currentProductSmall.image).into(holder.productImageImageView)
        holder.productNameTextView.text = currentProductSmall.productName
        holder.productTypeTextView.setProductType(when (currentProductSmall.category) {
            "MALE" -> ProductType.MALE
            "FEMALE" -> ProductType.FEMALE
            else -> ProductType.UNISEX
        })
        currentProductSmall.priceDiscount?.let {
            holder.productPriceTextView.text = "${currentProductSmall.priceDiscount}₺"
            holder.productOldPriceTextView.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                text = "${currentProductSmall.price}₺"
            }
        }?: run {
            holder.productOldPriceTextView.visibility = View.GONE
            holder.productPriceTextView.text = "${currentProductSmall.price}₺"
        }
        holder.itemView.setOnClickListener { onProductClickListener.onClick(position) }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}

interface OnProductClickListener{
    fun onClick(position: Int)
}