package com.itlong.andserver.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser

object JsonUtil {
    private const val TAG = "jsonUtil"

    /**
     * 转成json
     *
     * @param object
     * @return
     */
    fun toJsonString(`object`: Any?): String? {
        return Gson().toJson(`object`)
    }

    /**
     * 转成bean
     *
     * @param json json字符串
     * @param cls
     * @return
     */
    fun <T> toBean(json: String, cls: Class<T>?): T? {
        var t: T? = null
        try {
            t = Gson().fromJson(json, cls)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "toBean: json=$json")
        }
        return t
    }


    fun getValue(json: String, key: String?): String? {
        var eventType: String? = null
        try {
            val jsonObject =
                JsonParser().parse(json).asJsonObject
            if (jsonObject.has(key)) {
                eventType = jsonObject[key].asString
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Log.e(TAG, "getValue: json=$json")
        }
        return eventType
    }
}