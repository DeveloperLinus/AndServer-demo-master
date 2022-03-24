package com.itlong.andserver.controller

import com.yanzhenjie.andserver.annotation.GetMapping
import com.yanzhenjie.andserver.annotation.QueryParam
import com.yanzhenjie.andserver.annotation.RequestMapping
import com.yanzhenjie.andserver.annotation.RestController

// QueryParam仅用来获取 Url 中的参数
@RestController
@RequestMapping("/QueryParam")
class QueryParamController {
    @GetMapping("/user/info")
    fun info(@QueryParam(name = "id") id: Long): String {
        return "id->$id"
    }
}