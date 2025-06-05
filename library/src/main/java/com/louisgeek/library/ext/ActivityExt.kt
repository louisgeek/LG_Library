package com.louisgeek.library.ext

import androidx.core.app.NavUtils
import androidx.core.app.TaskStackBuilder
import androidx.fragment.app.FragmentActivity

/**
 * Created by louisgeek on 2021/11/18.
 */
fun FragmentActivity.navigationUpClick() {
    //clear  fragments  first
    if (supportFragmentManager.backStackEntryCount > 1) {
        supportFragmentManager.popBackStackImmediate()
        return
    }
    //
    val parentActivityName = NavUtils.getParentActivityName(this)
    //未设置 parentActivityName
    if (parentActivityName == null
        // parentActivityName 就是自己
        || parentActivityName == this.javaClass.name
    ) {
        //
        supportFinishAfterTransition()
        return
    }
    //
    val upIntent = NavUtils.getParentActivityIntent(this)
    if (NavUtils.shouldUpRecreateTask(this, upIntent!!)
        //isTaskRoot() 可以判断当前 Activity 是否是在当前 Task 的根 Activity
        || isTaskRoot
    ) {
        // This activity is NOT part of this app's task, so create a new task
        // when navigating up, with a synthesized back stack.
        TaskStackBuilder.create(this) // Add all of this activity's parents to the back stack
            .addNextIntentWithParentStack(upIntent) // Navigate up to the closest parent
            .startActivities()
    } else {
        //
        supportFinishAfterTransition()
    }
}
