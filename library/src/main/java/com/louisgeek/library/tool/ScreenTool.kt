package com.louisgeek.library.tool

import android.annotation.SuppressLint
import android.content.res.Resources

/**
 * Created by louisgeek on 2021/11/10.
 */
object ScreenTool {
    @JvmStatic
    val screenWidth = Resources.getSystem().displayMetrics.widthPixels

    //状态栏 + 标题栏 + 内容 + 导航栏
    @JvmStatic
    val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    @JvmStatic
    @SuppressLint("InternalInsetResource")
    fun getStatusBarHeight(): Int {
        var statusBarHeight = 0
        val resourceId =
            Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = Resources.getSystem().getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }

    @JvmStatic
    @SuppressLint("InternalInsetResource")
    fun getNavigationBarHeight(): Int {
        var navigationBarHeight = 0
        val resourceId =
            Resources.getSystem().getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            navigationBarHeight = Resources.getSystem().getDimensionPixelSize(resourceId)
        }
        return navigationBarHeight
    }
}