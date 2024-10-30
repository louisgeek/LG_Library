package com.louisgeek.library.tool

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.usb.UsbConstants
import android.hardware.usb.UsbDevice
import android.util.Log
import androidx.core.content.ContextCompat

object UsbTool {
    private const val TAG = "UsbTool"
    fun requestUsbPermissionCompat(
        context: Context,
        usbDevice: UsbDevice,
        requestCameraPermission: () -> Unit,
        requestUsbPermission: () -> Unit
    ) {
        //ps:https://developer.android.google.cn/reference/android/hardware/usb/UsbInstace.html#requestPermission(android.hardware.usb.UsbDevice,%20android.app.PendingIntent)
        if (context.applicationInfo.targetSdkVersion < 28) {
            //低版本直接请求usb
            requestUsbPermission.invoke()
            Log.e(TAG, "requestUsbPermissionCompat: 低版本直接请求usb")
        } else {
            //ps:在 targetSdkVersion >= 28 情况下,如果是 VIDEO CLASS 设备，需要有相机权限，否则会不弹【请求允许访问的对话框】
            if (isCameraDevicePresent(usbDevice)) {
                if (isCameraPermissionGranted(context)) {
                    Log.e(TAG, "requestUsbPermissionCompat: 高版本 已经有相机权限")
                    requestUsbPermission.invoke()
                } else {
                    Log.e(TAG, "requestUsbPermissionCompat: 高版本 还没有相机权限,去申请")
                    requestCameraPermission.invoke()
                }
            } else {
                Log.e(TAG, "requestUsbPermissionCompat: 高版本 不是 camera 设备")
                requestUsbPermission.invoke()
            }
        }
    }


    private fun isCameraDevicePresent(device: UsbDevice): Boolean {
        if (device.deviceClass == UsbConstants.USB_CLASS_VIDEO) {
            return true
        }
        for (i in 0 until device.interfaceCount) {
            val deviceInterface = device.getInterface(i)
            if (deviceInterface.interfaceClass == UsbConstants.USB_CLASS_VIDEO) {
                return true
            }
        }
        return false
    }

    private fun isCameraPermissionGranted(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

    }
}