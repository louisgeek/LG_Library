package com.louisgeek.library.player

/**
 * Created by louisgeek on 2019/4/25.
 */
interface IPlayListener {
    fun onStart()
    fun onPause()
    fun onResume()
    fun onStop()

    fun onComplete()
    fun onError(error: String)

    fun onSurfaceInvalid()
    fun onTimeout()
    fun onProgress(progress: Float)
}