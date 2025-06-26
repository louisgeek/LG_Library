package com.louis.lg_library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<View>(R.id.tv).setOnClickListener {


            val callable = object : Callable<String> {
                override fun call(): String {
                    Log.e(TAG, "call: do call")
                    return "dsds"
                }

            }
            Thread.sleep(30_000)
            val runnable = object : Runnable {
                override fun run() {
                    Log.e(TAG, "run: do run 111")
                    Thread.sleep(30_000)
                    Log.e(TAG, "run: do run 222")

                }

            }
            val futureTask = FutureTask(runnable, "resultsss")
            Thread(futureTask).start()
            Log.e(TAG, "run: do get 333")
            val result = futureTask.get()
            Log.e(TAG, "run: do get 444")


        }
    }
}