package com.itlong.andserver.controller

import com.yanzhenjie.andserver.annotation.Controller
import com.yanzhenjie.andserver.annotation.GetMapping
import com.yanzhenjie.andserver.annotation.ResponseBody
import com.yanzhenjie.andserver.framework.body.StringBody
import com.yanzhenjie.andserver.http.HttpRequest
import com.yanzhenjie.andserver.http.HttpResponse

// 添加了Controller注解的控制器中的方法的返回值经过ViewResolver分析，如果是ResponseBody则会直接写出到客户端、
// 如果是其它类型的数据会先经过MessageConverter转化成ResponseBody再输出到客户端，如果开发者没有提供MessageConverter怎会将返回值toString()后组成StringBody输出。
// 注：直接返回 Model 需要开发者提供MessageConverter来做数据转换，否则返回出去的 Model 将会被toString()后当作String输出
@Controller
class ShopController {
    @ResponseBody
    @GetMapping("/getFruits")
    fun getFruitsName(): String {
        return "apple, banana, lemon"
    }


    // fail forward:/index.html
    @GetMapping("/")
    fun index(): String {
        return "forward:/index.html"
    }

    // pass apple, banana, lemon
    @GetMapping("/fruits")
    fun forwardFruitsName() : String{
        return "forward:/getFruits"
    }

    // pass apple, banana, lemon
    @GetMapping("/redirect/fruits")
    fun redirectFruitsName():String {
        return "redirect:/getFruits"
    }

    // fail  Server internal error, The return value of [null] is not supported.
    @GetMapping("/getInfo")
    fun getInfo(request: HttpRequest, response: HttpResponse) {
        val content = "无返回值示例"
        val body = StringBody(content)
        response.setBody(body)
    }
}