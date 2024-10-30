package com.louis.lg_library.fixed

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

/**
 * Created by louisgeek on 2022/5/19.
 */
abstract class OnBackPressedCallbackFixed(
    private val fragment: Fragment,
    private val enabled: Boolean = true
) : OnBackPressedCallback(enabled) {

    override fun handleOnBackPressed() {
        this.onBackPressed()
    }

    private fun onBackPressed() {
        val fragmentActivity = fragment.requireActivity()
        //防止重复分发，先置 false 后恢复 true
        this.isEnabled = false
        //fragmentActivity.onBackPressed()
        fragmentActivity.onBackPressedDispatcher.onBackPressed()
        this.isEnabled = true
    }
}