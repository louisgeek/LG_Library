package com.louisgeek.library.tool

import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs

/**
 * Created by louisgeek on 2021/8/24.
 *
 * 逻辑上的内部存储,总是可用的
 *
 * LIS = LogicalInternalStorage
 *
 * @see LESTool
 *
 * LIS 对应 LES(PIS + PES)
 */
object LISTool {

    /**
     * /cache
     */
    @JvmStatic
    fun getDownloadCacheDirPath(): String? {
        return Environment.getDownloadCacheDirectory().absolutePath
    }


    /**
     * /system
     */
    @JvmStatic
    fun getRootDirPath(): String? {
        return Environment.getRootDirectory().absolutePath
    }

    /**
     * /data
     */
    @JvmStatic
    fun getDataDirPath(): String? {
        return Environment.getDataDirectory().absolutePath
    }

    /**
     * app 的 internal cache 目录
     * /data/data/<application package>/cache
     * miui 等系统应用多开时候
     * /data/user/0/<application package>/cache
     * miui 等系统应用多开时候 小号
     * /data/user/999/<application package>/cache
     */
    @JvmStatic
    fun getCacheDirPath(context: Context): String {
        return context.cacheDir.absolutePath
    }

    /**
     * app 的 internal files 目录
     * /data/data/<application package>/files
     * miui等系统应用多开 时候
     * /data/user/0/<application package>/files
     * miui等系统应用多开时候 小号
     * /data/user/999/<application package>/files
     */
    @JvmStatic
    fun getFilesDirPath(context: Context): String {
        return context.filesDir.absolutePath
    }

    @JvmStatic
    fun getLISTotalSpace(): Long {
        val path = getDataDirPath()
        val statFs = StatFs(path)
        return if (Build.VERSION.SDK_INT >= 18) {
            statFs.totalBytes
        } else {
            statFs.blockSizeLong * statFs.blockCountLong
        }
    }

    @JvmStatic
    fun getLISAvailableSpace(): Long {
        val path = getDataDirPath()
        val statFs = StatFs(path)
        return if (Build.VERSION.SDK_INT >= 18) {
            // getAvailableBytes 比 getFreeBytes 准确
            statFs.availableBytes
        } else {
            // getAvailableBlocksLong 比 getFreeBlocksLong 准确
            statFs.blockSizeLong * statFs.availableBlocksLong
        }
    }
}
