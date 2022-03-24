package com.itlong.andserver.controller

import com.yanzhenjie.andserver.annotation.GetMapping
import com.yanzhenjie.andserver.annotation.RestController
import com.yanzhenjie.andserver.http.HttpRequest

@RestController
class CookieValueController {
    @GetMapping("/cookie/get")
    fun cookieValue(request: HttpRequest) : String {
        val cookie = request.getCookie("account")
        val cookieValue = cookie?.let {
            it.value
        } ?: null
        return "cookieValue is $cookieValue"
    }
}