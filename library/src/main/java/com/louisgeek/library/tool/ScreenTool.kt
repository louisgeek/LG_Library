package com.louisgeek.library.tool

import android.content.res.Resources

/**
 * Created by louisgeek on 2021/11/10.
 */

object ScreenTool {
    val screenWidth = Resources.getSystem().displayMetrics.widthPixels

    @Deprecated("使用时候需注意")
    val screenHeight = Resources.getSystem().displayMetrics.heightPixels

}