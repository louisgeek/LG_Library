package com.louisgeek.library.tool

/**
 * Created by louisgeek on 2021/11/10.
 */
object ReflectTool {
    fun setFieldValueForSuperClass(tClass: Class<*>, obj: Any, fieldName: String, value: Any) {
        try {
            val field = tClass.getDeclaredField(fieldName)
            field.isAccessible = true
            field[obj] = value
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }

    /**
     * 反射获得对象的值
     * 常用于子类继承父类时候调用
     *
     * @param
     * @param fieldName
     * @return
     */
    fun <T> getFieldValueFromSuperClass(tClass: Class<*>, obj: Any, fieldName: String): T? {
        try {
            val field = tClass.getDeclaredField(fieldName)
            field.isAccessible = true
            return field[obj] as T
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 反射获得对象的值
     */
    /* fun <T, E> getFieldValue(eObj: E, fieldName: String?): T? {
         return getFieldValueFromSuperClass(eObj.javaClass.javaC, eObj, fieldName!!)
     }

     fun <E> setFieldValue(eObj: E, fieldName: String?, value: Any?) {
         setFieldValueForSuperClass(eObj.javaClass, eObj, fieldName!!, value!!)
     }*/
}