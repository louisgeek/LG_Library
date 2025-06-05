package com.louisgeek.library.tool

import android.content.Context
import android.view.Gravity
import android.widget.Toast

/**
 * Created by louisgeek on 2021/11/12.
 */
object ToastTool {
    private var toast: Toast? = null
    fun show(
        context: Context,
        text: CharSequence,
        duration: Int = Toast.LENGTH_SHORT,
        gravity: Int = Gravity.BOTTOM
    ) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration)
        }
        toast?.let {
            it.setText(text)
            it.duration = duration
            it.setGravity(gravity, 0, 0)
            it.show()
        }
    }
}