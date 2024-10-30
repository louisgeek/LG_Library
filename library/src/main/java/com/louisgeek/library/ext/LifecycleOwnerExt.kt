package com.louis.lg_library.ext

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner

/**
 * Created by louisgeek on 2022/5/19.
 */
fun LifecycleOwner.getContext(): Context? {
    var context: Context? = null
    if (this is FragmentActivity) {
        context = this
    } else if (this is Fragment) {
        context = this.context
    }
    return context
}