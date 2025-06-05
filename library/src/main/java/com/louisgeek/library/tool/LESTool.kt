package com.louisgeek.library.tool

import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.os.storage.StorageManager
import android.util.Log
import java.util.*

/**
 * Created by louisgeek on 2021/8/24.
 *
 * 逻辑上的外部存储,使用前需要判断是否可用
 *
 * LES = LogicalExternalStorage
 * PIS = PhysicalInternalStorage
 * PES = PhysicalExternalStorage
 *
 * LogicalExternalStorage =  PhysicalInternalStorage + PhysicalExternalStorage
 * LogicalExternalStorage：逻辑上的外部存储
 * PhysicalInternalStorage：内置 SD 卡 = 内置内存卡/内置 Sdcard/逻辑外部存储 /模拟存储
 * PhysicalExternalStorage：外置 SD 卡 = 外置内存卡/外置 Sdcard /物理外部存储/ Micro SD卡/ TF 卡
 *
 * 需要
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 *
 * @see LISTool
 *
 * LES(PIS + PES) 对应 LIS
 *
 */
object LESTool {
    private const val TAG = "LESTool"

    /**
     * read and write
     */
    @JvmStatic
    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    /**
     * private cache ：缓存文件
     * /storage/emulated/0/Android/data/<application package>/cache
     * 或
     * /storage/sdcard0/Android/data/<application package>/cache
     *
     * 注意：这下面的文件会在用户卸载我们的 app 时被系统删除
     * 对应 设置->应用->应用详情里面的"清除缓存"选项
     */
    @JvmStatic
    fun getExternalCacheDirPath(context: Context): String {
        return context.externalCacheDir!!.absolutePath
    }

    /**
     * private files ：数据文件
     * /storage/emulated/0/Android/data/<application package>/files
     * 或
     * /storage/sdcard0/Android/data/<application package>/files
     *
     * 注意：这下面的文件会在用户卸载我们的 app 时被系统删除
     * 对应 设置->应用->应用详情里面的"清除数据"选项
     */
    @JvmStatic
    fun getExternalFilesDirPath(context: Context): String {
        return context.getExternalFilesDir(null)!!.absolutePath
    }

    /**
     * public files
     * /storage/emulated/0/Download
     * 或
     * /storage/sdcard0/Download
     * 或
     * mnt/sdcard/Download
     * 其他
     * Environment.DIRECTORY_MUSIC 音乐
     * Environment.DIRECTORY_PODCASTS 音/视频的剪辑片段
     * Environment.DIRECTORY_ALARMS  闹钟的声音
     * Environment.DIRECTORY_RINGTONES  铃声
     * Environment.DIRECTORY_NOTIFICATIONS
     * Environment.DIRECTORY_PICTURES 图片
     * Environment.DIRECTORY_MOVIES 电影
     * Environment.DIRECTORY_DOWNLOADS 下载的内容
     * Environment.DIRECTORY_DCIM 拍摄的照片/视频
     * Environment.DIRECTORY_DOCUMENTS 文档
     */
    @JvmStatic
    fun getExternalStoragePublicDirPath(type: String = Environment.DIRECTORY_DOWNLOADS): String {
        return Environment.getExternalStoragePublicDirectory(type).absolutePath
    }

    /**
     * 获取内置 SD 卡路径
     * /storage/emulated/0  【/mnt/m_internal_storage】
     * 或
     * /storage/sdcard0
     *
     *
     * @see getPISPath
     */
    @JvmStatic
    fun getExternalStorageDirPath(): String {
        return Environment.getExternalStorageDirectory().absolutePath
    }

    @JvmStatic
    fun getExternalStorageDirAvailableSpace(): Long {
        val freeSpace = Environment.getExternalStorageDirectory().freeSpace
        val totalSpace = Environment.getExternalStorageDirectory().totalSpace
        Log.e(TAG, "getExternalStorageDirAvailableSpace: $freeSpace $totalSpace")
        val path = Environment.getExternalStorageDirectory().absolutePath
        val statFs = StatFs(path)
        return if (Build.VERSION.SDK_INT >= 18) {
            // getAvailableBytes 比 getFreeBytes 准确
            statFs.availableBytes
        } else {
            // getAvailableBlocksLong 比 getFreeBlocksLong 准确
            statFs.blockSizeLong * statFs.availableBlocksLong
        }
    }

    @JvmStatic
    fun isPESWritable(context: Context, path: String?): Boolean {
        try {
            val sm = context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
            val method =
                StorageManager::class.java.getMethod("getVolumeState", String::class.java)
            val state = method.invoke(sm, path) as String
            return state == Environment.MEDIA_MOUNTED
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * 获取所有存储路径：模拟存储 + SD/TF 卡
     */
    @JvmStatic
    private fun getLESPaths(context: Context): Array<String>? {
        try {
            val sm =
                context.getSystemService(Context.STORAGE_SERVICE) as StorageManager
            val getVolumePathsMethod =
                StorageManager::class.java.getMethod("getVolumePaths")
            //获取所有 sd 卡的路径
            return getVolumePathsMethod.invoke(sm) as Array<String>
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 获取内置内存卡 Physical Internal Storage / Physical UnRemovable Storage 路径
     * 例： /storage/emulated/0   【/mnt/m_internal_storage】
     *  或 /storage/sdcard0
     *
     *  @see getExternalStorageDirectoryPath
     */
    @JvmStatic
    fun getPISPath(context: Context): String? {
        val paths = getLESPaths(context)
        paths?.let {
            if (it.isNotEmpty()) {
                //第一张：内置 SD 卡
                return paths[0]
            }
        }
        return null
    }

    /**
     * 获取其他所有可用 外置内存卡 Physical External Storage / Physical Removable Storage 路径 集合
     * 可能是/mnt/sdcard、/mnt/extsd、/mnt/external_sd 、/mnt/sdcard2
     */
    @JvmStatic
    fun getPESPathList(context: Context): List<String>? {
        val paths = getLESPaths(context)
        //从第二张开始  外置 SD 卡
        paths?.let {
            if (paths.size > 1) {
                val sdPathList: MutableList<String> = ArrayList()
                for (i in 1 until paths.size) {
                    if (isPESWritable(context, paths[i])) {
                        sdPathList.add(paths[i])
                    }
                }
                return sdPathList
            }
        }
        return null
    }

    /**
     * 获取第一个外置内存卡 External storage / Removable storage 路径
     * 可能是/mnt/sdcard、/mnt/extsd、/mnt/external_sd 、/mnt/sdcard2
     *
     * 例：
     * /storage/sdcard1  【/mnt/m_external_sd】
     * 真机：
     * /storage/sdcard1     华为 M2-A01W
     * /storage/E8C3-9C76   红米 NOTE 4X
     * /storage/6279-371D   Galaxy Tab S2  SM-T819C
     * /storage/0403-0201   华为 nova 2s
     */
    @JvmStatic
    fun getPESPath(context: Context): String? {
        val pesPathList = getPESPathList(context)
        pesPathList?.let {
            if (pesPathList.isNotEmpty()) {
                return pesPathList[0]
            }
        }
        return null
    }

    @JvmStatic
    fun getPISTotalSpace(context: Context): Long {
        //获取 模拟存储
        val path = getPISPath(context)
        if (path.isNullOrEmpty()) {
            return 0
        }
        val statFs = StatFs(path)
        return if (Build.VERSION.SDK_INT >= 18) {
            statFs.totalBytes
        } else {
            statFs.blockSizeLong * statFs.blockCountLong
        }
    }

    @JvmStatic
    fun getPISAvailableSpace(context: Context): Long {
        //获取内置 SD 卡
        val path = getPISPath(context)
        if (path.isNullOrEmpty()) {
            return 0
        }
        val statFs = StatFs(path)
        return if (Build.VERSION.SDK_INT >= 18) {
            // getAvailableBytes 比 getFreeBytes 准确
            statFs.availableBytes
        } else {
            // getAvailableBlocksLong 比 getFreeBlocksLong 准确
            statFs.blockSizeLong * statFs.availableBlocksLong
        }.also {
            val mb = it * 1.0F / 1024 / 1024
            val mbText = String.format(Locale.CHINA, "%.2f", mb)
            val gb = it * 1.0F / 1024 / 1024 / 1024;
            val gbText = String.format(Locale.CHINA, "%.2f", gb)
            Log.e(TAG, "getPISAvailableSpace: $mbText  $gbText")
        }
    }
}
