package com.itlong.andserver.controller

import android.util.Log
import com.itlong.andserver.User
import com.itlong.andserver.utils.FileUtil
import com.itlong.andserver.utils.JsonUtil
import com.yanzhenjie.andserver.annotation.*
import com.yanzhenjie.andserver.framework.body.JsonBody
import com.yanzhenjie.andserver.http.HttpRequest
import com.yanzhenjie.andserver.http.HttpResponse
import com.yanzhenjie.andserver.http.RequestBody
import com.yanzhenjie.andserver.http.ResponseBody
import com.yanzhenjie.andserver.http.multipart.MultipartFile

@RestController
@RequestMapping("/request/param")
class RequestParamController {
    // RequestParam支持注解的参数类型包括：MultipartFile、String、int、long、float、double、boolean
    // 参数可以是url中的参数，表单中的参数，postman中选择form-data,叶可以是Body中的参数，客户端的
    //content-Type是application/x-www-form-urlencoded
    @PostMapping("/login")
    fun login(
        @RequestParam("account") account: String,
        @RequestParam("password") password: String,
        @RequestParam(name = "id", required = false, defaultValue = "12") id: Long
    ): String {
        return "login success, account->$account and password->$password and id->$id"
    }

    @PostMapping("/upload")
    fun upload(@RequestParam("photo") photo: MultipartFile):String {
        Log.d("AndServer", "upload photo received, name->${photo.name}")
        val serverFile = FileUtil.getFile("sdcard/test.jpg")
        photo.transferTo(serverFile)
        return "upload file success"
    }

    @PostMapping("/test2")
    fun test2(request: HttpRequest) {
        Log.d("AndServer", "request-> account: ${request.getParameter("account")}, password:${request.getParameter("password")}")
    }

    // fail ,i don't understanding HttpResponse's function
    @GetMapping("/test3")
    fun test3(response: HttpResponse) {
        Log.d("AndServer", "receive data")
       response.setBody(JsonBody(JsonUtil.toJsonString(User("huqinghui", "aaaaaa"))))
    }

    // x-www-form-urlencoded
    @PostMapping("/test4")
    fun test4(body: RequestBody, response: HttpResponse) {
        Log.d("AndServer", "receive data, ${body.string()}")
        response.setBody(JsonBody(JsonUtil.toJsonString(User("huqinghui", "aaaaaa"))))
    }
}