/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.louisgeek.library.data

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
//import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 * <p>
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 * <p>
 * Note that only one observer is going to be notified of changes.
 *
 *
 * SingleLiveEvent class from https://github.com/android/architecture-samples
 * 一个只会触发一次数据更新的 LiveData，适用于只需要一个观察者接收一次更新的场景（如果是存在多个观察者，只有一个能够正常收到通知）
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {
    companion object {
        private const val TAG = "SingleLiveEvent"
    }

    private val pending = AtomicBoolean(false) //pending 即将发生的

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
//            Timber.w("Multiple observers registered but only one will be notified of changes.")
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner) {
            //expectedValue 预期值 true，最终设置成 false
            if (pending.compareAndSet(true, false)) {
                //compareAndSet 操作成功
                observer.onChanged(it)
            }
        }
    }

    @MainThread
    override fun setValue(value: T?) {
        //设置成 true
        pending.set(true)
        super.setValue(value)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }
}