package com.louis.lg_library.ext

import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Created by louisgeek on 2022/5/19.
 */

fun BottomNavigationView.actionSelectedItemId(itemId: Int) {
    this.selectedItemId - itemId
}

fun BottomNavigationView.actionSelectedItem(position: Int) {
    if (position >= 0 && position < this.menu.size()) {
        val itemId = this.menu.getItem(position).itemId
        this.actionSelectedItemId(itemId)
    }
}

fun BottomNavigationView.setSelectedItem(position: Int) {
    if (position >= 0 && position < this.menu.size()) {
        val itemId = this.menu.getItem(position).itemId
        val item = this.menu.findItem(itemId)
        if (item != null) {
            item.isChecked = true
        }
    }
}