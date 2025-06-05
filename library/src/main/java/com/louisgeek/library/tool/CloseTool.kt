package com.louisgeek.library.tool

import java.io.Closeable
import java.io.IOException

/**
 * Created by louisgeek on 2021/11/2.
 */
@Deprecated("use T.use")
object CloseTool {
    fun close(closeable: Closeable?) {
        try {
            closeable?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}