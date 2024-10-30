package com.louisgeek.library.player

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