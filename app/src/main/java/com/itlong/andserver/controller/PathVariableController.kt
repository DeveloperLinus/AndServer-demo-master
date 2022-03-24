package com.itlong.andserver.controller

import com.yanzhenjie.andserver.annotation.GetMapping
import com.yanzhenjie.andserver.annotation.PathVariable
import com.yanzhenjie.andserver.annotation.RestController

// 只能用在方法参数上，用来获取客户端的path参数

@RestController
class PathVariableController {

    // pass
    @GetMapping("/getUserId1/{userId}")
    fun getUserId1(@PathVariable("userId") id: Long): String {
        return "userId is $id"
    }

    // pass
    @GetMapping("/getUserId2/{userId}")
    fun getUserId2(@PathVariable("userId") id: String): String {
        return "userId is $id"
    }


    // pass
    @GetMapping("/getUserId3/{userId}")
    fun getUserId3(@PathVariable("userId") id: Boolean): String {
        return "userId is $id"
    }
}