package com.louisgeek.library.ext

/**
 * Created by zhufangquan on 2021/11/12.
 */

fun <K, V> MutableMap<K, V>.findKey(value: V): K? {
    var key: K? = null
    for ((kk, vv) in this.entries) {
        if (value == vv) {
            key = kk
        }
    }
    return key

}

