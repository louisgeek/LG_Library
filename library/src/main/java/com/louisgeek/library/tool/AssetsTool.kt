package com.louisgeek.library.tool

import android.util.Log
import com.louisgeek.library.LibraryProvider
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**
 * Created by zhufangquan on 2021/11/2.
 */
object AssetsTool {
    private const val TAG = "AssetsTool"

    /**
     * @param context
     * @param assetsFilePath "aaa.txt" or "temp/bbb.txt"
     * @param filePath       "/storage/emulated/0/aaa.txt" or "/storage/emulated/0/temp/bbb.txt"
     */
    fun copyAssetsFile(
        assetsFilePath: String,
        filePath: String,
        overwrite: Boolean = false
    ): Boolean {
        var success = true

        //目标路径
        val file = File(filePath)
        file.parentFile?.let { fileParentDir ->
            if (!fileParentDir.exists()) {
                fileParentDir.mkdirs()
            }
        }
        if (overwrite) {
            if (file.exists()) {
                file.delete()
            }
        } else {
            if (!file.exists()) {
                file.createNewFile()
            }
        }
        var ins: InputStream? = null
        var fos: FileOutputStream? = null
        try {
            ins = LibraryProvider.getContext().assets.open(assetsFilePath)
            fos = FileOutputStream(filePath)
            val buffer = ByteArray(1024)
            var len: Int
            while (ins.read(buffer).also { len = it } != -1) {
                fos.write(buffer, 0, len)
            }
            //刷新缓冲区
            fos.flush()
        } catch (e: Exception) {
//            e.printStackTrace()
            //如果拷贝失败 删除刚建的空文件
            if (file.exists()) {
                file.delete()
            }
            Log.e(TAG, "copyAssetsFile: ", e)
            success = false
        } finally {
            CloseTool.close(fos)
            CloseTool.close(ins)
        }
        return success
    }
}