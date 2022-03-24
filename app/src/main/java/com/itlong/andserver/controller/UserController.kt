package com.itlong.andserver.controller

import android.util.Log
import com.itlong.andserver.User
import com.itlong.andserver.utils.JsonUtil
import com.yanzhenjie.andserver.annotation.*
import com.yanzhenjie.andserver.framework.body.JsonBody
import com.yanzhenjie.andserver.http.HttpRequest

@RestController
@RequestMapping
class UserController {
    @PostMapping("/login")
    fun login(@RequestParam("account") account:String, @RequestParam("password")password:String): JsonBody {
        Log.d("Http", "login account->$account, password->$password")
        return JsonBody(JsonUtil.toJsonString(User(account, password)))
    }

    @GetMapping("/connect")
    fun connect(request: HttpRequest): String {
        Log.d("Http", "connect ip->${request.localAddr}")
        return request.localAddr
    }
}