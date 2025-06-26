package com.louis.lg_library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.louisgeek.library.tool.ScreenTool
import com.louisgeek.library.tool.ToastTool
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<View>(R.id.tv).setOnClickListener {
            val screenWidth = ScreenTool.screenWidth
            ToastTool.show(this, "screenWidth=$screenWidth")
        }
    }
}