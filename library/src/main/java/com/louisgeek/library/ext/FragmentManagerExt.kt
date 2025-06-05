package com.louisgeek.library.ext

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by louisgeek on 2021/11/18.
 */
fun FragmentManager.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = true
) {
    val tag = fragment.javaClass.canonicalName
    val fragmentTransaction = this.beginTransaction()
    fragmentTransaction.replace(containerId, fragment, tag)
    if (addToBackStack) {
        //如果addToBackStack调用了 那么后期 remove 不能出栈 需要用 popBackStackXXX 方法出栈
        //fragmentTransaction.addToBackStack(null)
        fragmentTransaction.addToBackStack(tag)
    }
    fragmentTransaction.commitAllowingStateLoss()
}

@Deprecated("parentFragmentManager.popBackStackImmediate()   navigationUpClick ")
private fun FragmentManager.backPressed() {
    //弹出回退栈中最上层的fragment
//    this.popBackStackImmediate(null, 0)
    //弹出回退栈中所有fragment
//    this.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

}

fun FragmentManager.showDialog(
    dialogFragment: DialogFragment
) {
    val tag = dialogFragment.javaClass.canonicalName
    dialogFragment.show(this, tag)

}

