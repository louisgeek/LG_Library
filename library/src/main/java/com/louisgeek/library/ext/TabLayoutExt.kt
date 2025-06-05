package com.louisgeek.library.ext

import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayout

/**
 * Created by louisgeek on 2022/5/19.
 */

fun TabLayout.selectTabPosition(position: Int, updateIndicator: Boolean = true) {
    this.selectTab(this.getTabAt(position), updateIndicator)
}

fun TabLayout.addTab(@StringRes textResId: Int) {
    this.addTab(this.newTab().setText(textResId))
}

fun TabLayout.addTab(text: String) {
    this.addTab(this.newTab().setText(text))
}