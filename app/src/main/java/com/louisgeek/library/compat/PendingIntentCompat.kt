package com.louisgeek.library.compat

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build

/**
 * Created by louisgeek on 2022/5/19.
 */
class PendingIntentCompat {
    fun getBroadcast(context: Context, action: String): PendingIntent {
        val intent = Intent(action)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            //仅当某些功能依赖于可变的 PendingIntent 时才使用 FLAG_MUTABLE
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }
    }
}