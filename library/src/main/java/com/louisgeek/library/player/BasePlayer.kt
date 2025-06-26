package com.louisgeek.library.player

import android.os.CountDownTimer
import android.util.Log
import android.view.SurfaceHolder
import java.util.concurrent.CopyOnWriteArrayList

/**
 * Created by louisgeek on 2019/4/25.
 */
abstract class BasePlayer(private val holder: SurfaceHolder?) : IPlayer {
    companion object {
        private const val TAG = "BasePlayer"
    }

    private var currentUri: String = ""
    private var currentPosition: Long = 0
    private var totalDuration: Long = 0

    protected val playListeners: CopyOnWriteArrayList<IPlayListener> = CopyOnWriteArrayList()
    override fun addPlayListener(listener: IPlayListener) {
        val success = playListeners.addIfAbsent(listener)
        Log.e(TAG, "addPlayListener:$success " + playListeners.size)
    }

    override fun removePlayListener(listener: IPlayListener) {
        playListeners.remove(listener)
        Log.e(TAG, "removePlayListener:zzz " + playListeners.size)
    }

    protected var countDownTimer =
        //最多等待3~5秒
        object : CountDownTimer(5_000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //0.1 修正秒
                val second = (millisUntilFinished / 1000 + 0.1).toInt()
                Log.e(TAG, "ssq onTick: $second")
            }

            override fun onFinish() {
                Log.e(TAG, "ssq onFinish: ")
                for (listener in playListeners) {
                    listener.onTimeout()
                }
                //!!!
                stop()
            }
        }

    override fun start(uri: String, position: Int) {
        if (holder == null || !holder.surface.isValid) {
            Log.e(TAG, "start: surfaceHolder is un ready")
            for (listener in playListeners) {
                listener.onSurfaceInvalid()
            }
            return
        }
        //
        for (listener in playListeners) {
            listener.onStart()
        }
        //
        countDownTimer.start()
        //
        /* for (listener in playListeners) {
             listener.onProgress(0F)
         }*/
        //

    }


    override fun pause() {
        for (listener in playListeners) {
            listener.onPause()
        }
    }

    override fun resume() {
        for (listener in playListeners) {
            listener.onResume()
        }
    }

    override fun stop() {
        //!!
        countDownTimer.cancel()
        //
        for (listener in playListeners) {
            listener.onStop()
        }
    }


    override fun getCurrentPosition(): Long {
        return currentPosition
    }

    override fun getTotalDuration(): Long {
        return totalDuration
    }

    override fun getCurrentUri(): String {
        return currentUri
    }
    /*   for (listener in playListeners) {
               listener.onError(error)
           }

            interface PlayListener {
           fun onSurfaceInvalid()
           fun onPlayStart()
           fun onPlayStop()
           fun onPlayTimeout()
           fun onPlayError(error: String)
           fun onPlayProgress(progress: Float)
       }*/
}