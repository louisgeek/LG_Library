package com.louisgeek.library.ext

import android.Manifest
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.annotation.RequiresPermission
import com.louisgeek.library.tool.ToastTool

/**
 * Created by louisgeek on 2021/11/10.
 */
fun Context.toast(text: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
    if (text.isNullOrEmpty()) {
        return
    }
    ToastTool.show(this, text, duration)
}

//500  100
@RequiresPermission(Manifest.permission.VIBRATE)
fun Context.vibrator(milliseconds: Long = 100) {
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(
            VibrationEffect.createOneShot(
                milliseconds,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    } else {
        vibrator.vibrate(milliseconds)
    }
}

fun Context.playRingtoneNotification() {
    //初始化 系统声音
    val rm = RingtoneManager(this)
    //获取系统声音路径
    val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    //通过Uri 来获取提示音的实例对象
    val ringtone = RingtoneManager.getRingtone(this, uri)
    ringtone.play()
}