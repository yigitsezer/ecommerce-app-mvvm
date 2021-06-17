package com.yigitsezer.ecommerceapp.util

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.viewpager2.widget.ViewPager2
import com.yigitsezer.ecommerceapp.R
import kotlinx.coroutines.delay

fun TextView.setProductType(productType: ProductType) {
    val newBackground = ContextCompat.getDrawable(context, R.drawable.product_type_background) as GradientDrawable
    newBackground.setColor(when (productType) {
        ProductType.MALE -> ContextCompat.getColor(context, R.color.male_bg)
        ProductType.FEMALE -> ContextCompat.getColor(context, R.color.female_bg)
        else -> ContextCompat.getColor(context, R.color.unisex_bg)
    })
    this.background = newBackground

    this.setTextColor(when (productType) {
        ProductType.MALE -> ContextCompat.getColor(context, R.color.male_text)
        ProductType.FEMALE -> ContextCompat.getColor(context, R.color.female_text)
        else -> ContextCompat.getColor(context, R.color.unisex_text)
    })
    this.text = when (productType) {
        ProductType.MALE -> context.getString(R.string.male)
        ProductType.FEMALE -> context.getString(R.string.female)
        else -> context.getString(R.string.unisex)
    }
}

fun View.setPicked(value: Boolean) {
    /*
    val newBackground = ContextCompat.getDrawable(context, R.drawable.picker_background) as GradientDrawable
    newBackground.setStroke((2 * resources.displayMetrics.density).toInt(), when (value) {
        true -> ContextCompat.getColor(context, R.color.box_tint_green)
        false -> ContextCompat.getColor(context, R.color.box_tint_grey)
    })*/
    if (value) background = ContextCompat.getDrawable(context, R.drawable.picker_background_green)
    else background = ContextCompat.getDrawable(context, R.drawable.picker_background_grey)
}

fun TextView.setActive(value: Boolean) {
    val newBackground = ContextCompat.getDrawable(context, R.drawable.button_background) as GradientDrawable
    newBackground.setColor(when (value) {
        true -> ContextCompat.getColor(context, R.color.button_green)
        false -> ContextCompat.getColor(context, R.color.button_grey)
    })
    this.background = newBackground
}

fun ViewPager2.autoScroll(lifecycleScope: LifecycleCoroutineScope, interval: Long) {
    lifecycleScope.launchWhenResumed {
        scrollIndefinitely(interval)
    }
}

private suspend fun ViewPager2.scrollIndefinitely(interval: Long) {
    delay(interval)
    val numberOfItems = adapter?.itemCount ?: 0
    val lastIndex = if (numberOfItems > 0) numberOfItems - 1 else 0
    val nextItem = if (currentItem == lastIndex) 0 else currentItem + 1

    setCurrentItem(nextItem, true)

    scrollIndefinitely(interval)
}