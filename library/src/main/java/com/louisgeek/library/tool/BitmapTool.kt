package com.louisgeek.library.tool

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Size
import androidx.annotation.ColorInt

/**
 * Created by louisgeek on 2024/5/19.
 */
object BitmapTool {

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        val width = drawable.intrinsicWidth
        val height = drawable.intrinsicHeight
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas()
        canvas.setBitmap(bitmap)

        drawable.setBounds(0, 0, width, height)
        drawable.draw(canvas)
        return bitmap
    }


    fun bitmapToArgbPixels(bitmap: Bitmap): IntArray {
        val argbPixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(argbPixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        return argbPixels
    }

    fun argbPixelsToBitmap(@ColorInt argbPixels: IntArray, size: Size): Bitmap {
        val bitmap = Bitmap.createBitmap(size.width, size.height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(argbPixels, 0, size.width, 0, 0, size.width, size.height)
        return bitmap
    }
}