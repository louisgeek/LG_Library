package com.louisgeek.library.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * Created by louisgeek on 2022/5/19.
 */
fun ImageView.setImageDrawableResource(@DrawableRes resId: Int) {
    //官方不推荐直接使用 setImageResource
    this.setImageDrawable(ContextCompat.getDrawable(this.context, resId))
}