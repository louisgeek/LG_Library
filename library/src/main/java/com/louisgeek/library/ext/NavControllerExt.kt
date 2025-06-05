package com.louisgeek.library.ext

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions

/**
 * Created by louisgeek on 2022/5/19.
 */
fun NavController.navigateTry(@IdRes resId: Int, args: Bundle?, navOptions: NavOptions?) {
    try {
        this.navigate(resId, args, navOptions)
    } catch (e: Exception) {
//        e.printStackTrace()
        Log.d("NavController", "navigateTry: ", e)
    }
}

fun NavController.navigateFixed(
    @IdRes currentDestinationId: Int,
    @IdRes resId: Int,
    args: Bundle?,
    navOptions: NavOptions?
) {
    if (this.currentDestination?.id == currentDestinationId) {
        this.navigate(resId, args, navOptions)
    } else {
        Log.d("NavController", "navigateFixed: ")
    }
}


