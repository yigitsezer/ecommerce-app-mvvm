package com.yigitsezer.ecommerceapp.ui.product

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yigitsezer.ecommerceapp.R
import com.yigitsezer.ecommerceapp.util.setPicked


class SizePickersAdapter(private var sizes: List<String>, var selectedItem: Int, var onSizeClickListener: OnSizeClickListener): RecyclerView.Adapter<SizePickersAdapter.SizePickerViewHolder>() {
    class SizePickerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val sizeTextView: TextView = view.findViewById(R.id.tv_size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizePickerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_size_picker, parent, false)
        return SizePickerViewHolder(view)
    }

    override fun onBindViewHolder(holder: SizePickerViewHolder, position: Int) {
        holder.sizeTextView.text = sizes[position]
        if (position == selectedItem) {
            holder.sizeTextView.setPicked(true)
        }
        holder.itemView.setOnClickListener {
            onSizeClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return sizes.size
    }
}

interface OnSizeClickListener{
    fun onClick(position: Int)
}