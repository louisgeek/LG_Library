package com.louisgeek.library

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.tencent.mmkv.MMKV
import java.util.*

/**
 * Created by zhufangquan on 2021/11/2.
 */
internal class LibraryProviderInitializer : Initializer<LibraryProvider> {
    companion object {
        private const val TAG = "LPInitializer"
    }

    override fun create(context: Context): LibraryProvider {
        LibraryProvider.init(context);
        val rootDir: String = MMKV.initialize(context)
        println("mmkv root: $rootDir")
        Log.e(TAG, "create: LibraryProvider init")
        return LibraryProvider
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return Collections.emptyList()
//       return mutableListOf()
//        return mutableListOf(LogInitializer::class.java)
    }

}