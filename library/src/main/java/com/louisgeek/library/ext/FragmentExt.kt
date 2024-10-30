package com.louisgeek.library.ext

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.louisgeek.library.tool.ToastTool

/**
 * Created by zhufangquan on 2021/11/10.
 */
fun Fragment.toast(text: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
    if (text.isNullOrEmpty()) {
        return
    }
    ToastTool.show(this.requireContext(), text, duration)
}

fun Fragment.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    ToastTool.show(this.requireContext(), getString(resId), duration)
}

fun Fragment.backPressed() {
    if (isAdded) {
        requireActivity().navigationUpClick()
    }
}