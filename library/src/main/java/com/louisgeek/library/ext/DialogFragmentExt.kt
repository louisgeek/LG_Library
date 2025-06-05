package com.louisgeek.library.ext

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.louisgeek.library.tool.ReflectTool

/**
 * Created by louisgeek on 2021/11/12.
 */

fun DialogFragment.showAllowingStateLoss(manager: FragmentManager, tag: String) {
    ReflectTool.setFieldValueForSuperClass(DialogFragment::class.java, this, "mDismissed", false)
    ReflectTool.setFieldValueForSuperClass(DialogFragment::class.java, this, "mShownByMe", true)
    manager.beginTransaction().add(this, tag).commitAllowingStateLoss()
}

fun DialogFragment.isDismissed(): Boolean {
    return ReflectTool.getFieldValueFromSuperClass(DialogFragment::class.java, this, "mDismissed")
        ?: true
}