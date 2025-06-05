package com.louisgeek.library.tool

import android.util.Log
import java.io.*
import java.nio.charset.Charset

/**
 * Created by louisgeek on 2021/11/16.
 */
object FileTool {
    private const val TAG = "FileTool"
    fun saveFile(dirPath: String, fileName: String, data: ByteArray, len: Int): Boolean {
        val file = File(dirPath, fileName)
        if (file.exists()) {
            file.delete()
        } else {
            file.parentFile?.also { dirPathFile ->
                if (!dirPathFile.exists()) {
                    dirPathFile.mkdirs()
                }
            }
        }
        file.createNewFile()
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
//            fos.write(data, 0, data.size)
            //data chang du bu dui
            Log.e(TAG, "saveFile: " + len)
            fos.write(data, 0, len)
            //刷新缓冲区
            fos.flush()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            CloseTool.close(fos)
        }
        return false
    }

    fun writeText(filePath: String, text: String): Boolean {
        try {
            val outputStream = FileOutputStream(filePath)
            outputStream.write(text.toByteArray())
            //刷新缓冲区
            outputStream.flush()
            outputStream.close()
            return true
        } catch (e: Exception) {
//            e.printStackTrace()
        }
        return false
    }

    fun readText(filePath: String): String {
        try {
            val fileReader = FileReader(filePath)
            val bufferedReader = BufferedReader(fileReader)
            val text = bufferedReader.readLine()
            bufferedReader.close()
            fileReader.close()
            return text
        } catch (e: Exception) {
//            e.printStackTrace()
        }
        return ""
    }

    //
    fun readText2(filePath: String): String {
        val stringBuilder = StringBuilder()
        try {
            val inputStream = FileInputStream(filePath)
            //
            val bufferBytes = ByteArray(1024)
            var length: Int
            while (inputStream.read(bufferBytes).also { length = it } != -1) {
//                stringBuilder.append(String(bufferBytes, charset))
                val charset = Charset.forName("UTF-8")
                stringBuilder.append(String(bufferBytes, 0, length, charset))
            }
            inputStream.close()
            return stringBuilder.toString()
        } catch (e: Exception) {
            Log.e(TAG, "readText: ", e)
        }
        return ""
    }


}