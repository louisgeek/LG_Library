package com.louisgeek.library.ext

import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.DrawableRes

/**
 * Created by louisgeek on 2022/5/19.
 */
fun TextView.setTextSizePx(size: Float) {
    this.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
}

fun TextView.setDrawableResourceStart(@DrawableRes startResId: Int) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(startResId, 0, 0, 0)
}

fun TextView.setDrawableResourceTop(@DrawableRes topResId: Int) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, topResId, 0, 0)
}

fun TextView.setDrawableResourceEnd(@DrawableRes endResId: Int) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, endResId, 0)
}

fun TextView.setDrawableResourceBottom(@DrawableRes bottomResId: Int) {
    this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, bottomResId)
}