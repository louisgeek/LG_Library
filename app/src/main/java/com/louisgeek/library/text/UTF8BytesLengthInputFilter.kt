package com.louisgeek.library.text

import android.text.InputFilter
import android.text.Spanned

/**
 * Created by louisgeek on 2024/5/19.
 * UTF-8 ( maxBytes / 3 ) 个中文字
 */
class UTF8BytesLengthInputFilter(private val maxBytes: Int) : InputFilter {

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        var srcByteCount = 0
        // count UTF-8 bytes in source substring
        for (i in start until end) {
            val c = source[i]
            srcByteCount += if (c < 0x0080.toChar()) 1 else if (c < 0x0800.toChar()) 2 else 3
        }
        val destLen = dest.length
        var destByteCount = 0
        // count UTF-8 bytes in destination excluding replaced section
        for (i in 0 until destLen) {
            if (i < dstart || i >= dend) {
                val c = dest[i]
                destByteCount += if (c < 0x0080.toChar()) 1 else if (c < 0x0800.toChar()) 2 else 3
            }
        }
        var keepBytes = maxBytes - destByteCount
        if (keepBytes <= 0) {
            return ""
        } else if (keepBytes >= srcByteCount) {
            return null // use original dest string
        } else {
            // find end position of largest sequence that fits in keepBytes
            for (i in start until end) {
                val c = source[i]
                keepBytes -= if (c < 0x0080.toChar()) 1 else if (c < 0x0800.toChar()) 2 else 3
                if (keepBytes < 0) {
                    return source.subSequence(start, i)
                }
            }
            // If the entire substring fits, we should have returned null
            // above, so this line should not be reached. If for some
            // reason it is, return null to use the original dest string.
            return null
        }
    }
}