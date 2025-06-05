package com.louisgeek.library.tool

import com.tencent.mmkv.MMKV

/**
 * Created by louisgeek on 2021/10/8.
 */

object KVTool {
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return MMKV.defaultMMKV().decodeBool(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return MMKV.defaultMMKV().decodeString(key, defaultValue)!!
    }

    fun putString(key: String, value: String) {
        MMKV.defaultMMKV().encode(key, value)
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return MMKV.defaultMMKV().decodeInt(key, defaultValue)
    }

    fun putInt(key: String, value: Int) {
        MMKV.defaultMMKV().encode(key, value)
    }
}