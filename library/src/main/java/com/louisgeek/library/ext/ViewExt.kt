package com.louisgeek.library.ext

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.louisgeek.library.R

/**
 * Created by louisgeek on 2021/11/15.
 */
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm.isActive) {
        imm.hideSoftInputFromWindow(windowToken, 0)
        imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

}

//fun View.setTagHolder(tag: Any) {
//    this.setTag(R.id.id_view_tag_holder, tag)
//}
//
//fun View.getTagHolder(): Any? {
//    return this.getTag(R.id.id_view_tag_holder)
//}