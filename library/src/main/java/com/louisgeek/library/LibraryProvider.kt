package com.louisgeek.library

import android.content.Context

/**
 * Created by louisgeek on 2021/11/2.
 */
internal object LibraryProvider {
    private lateinit var appContext: Context

    fun init(appContext: Context) {
        LibraryProvider.appContext = appContext
    }

    fun getContext(): Context {
        return appContext
    }


}