package com.louisgeek.library.ext

import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * Created by louisgeek on 2024/5/19.
 */
fun Fragment.setImageDrawableResource(@DrawableRes resId: Int) {
    val xxx =
        this.registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            val xxx = result
        }
}