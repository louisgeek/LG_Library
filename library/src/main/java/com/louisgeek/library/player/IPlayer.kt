package com.louisgeek.library.player

/**
 * Created by louisgeek on 2019/4/25.
 */
interface IPlayer {

    fun init()

    fun start(uri: String, position: Int = 0)
    fun pause()
    fun resume()
    fun stop()

    fun release()

    fun seekTo(position: Int)
    fun getCurrentUri(): String
    fun getTotalDuration(): Long
    fun getCurrentPosition(): Long
    fun isPlaying(): Boolean

    fun takePhoto(filePath: String): Boolean
    fun startRecord(filePath: String)
    fun stopRecord()


    fun addPlayListener(listener: IPlayListener)
    fun removePlayListener(listener: IPlayListener)
}