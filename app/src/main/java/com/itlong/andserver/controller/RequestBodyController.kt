package com.itlong.andserver.controller

import android.util.Log
import com.itlong.andserver.User
import com.itlong.andserver.utils.JsonUtil
import com.yanzhenjie.andserver.annotation.*


//@Controller
//@ResponseBody 结合ResponseBody即可实现@ResetController的效果，直接将服务端返回的内容返回客户端，否则会经过ViewResolver->MessageConverter

// 客户端可以把一段JSON或者一个文件整体写到请求的Body中发送服务端，服务端的处理方法有两种
//@ResponseBody
@RestController
class RequestBodyController {
    // 第一种是拿到RequestBody对象，由开发者自行转换为目标对象
    @PostMapping("/push/user")
    fun  push(body: com.yanzhenjie.andserver.http.RequestBody) : String {
        val content = body.string()
        val user = JsonUtil.toBean(content, User::class.java)
        Log.d("AndServer",  "account->${user?.account}, password->${user?.password}")
        return "push user success"
    }

    // test fail
    // 第二种是使用RequestBody注解结合MessageConverter自动转换为目标对象
    @PostMapping("/push/user2")
    fun push2(@RequestBody user: User) : String{
        Log.d("AndServer",  "account->${user.account}, password->${user.password}")
        return "push user2 success"
    }
}