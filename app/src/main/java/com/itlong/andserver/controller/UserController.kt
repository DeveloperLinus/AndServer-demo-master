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

    @GetMapping("/")
    fun index():String {
        return "forward:/index.html"
    }

    @ResponseBody
    @GetMapping("/project/info")
    fun newInfo(): String {
        return "I am new api."
    }

    @GetMapping("/projectInfo")
    fun oldInfo(): String {
        return "redirect:/project/info"
    }


    @GetMapping("/connection")
    fun getConnection(request: HttpRequest): Any {
        return HashMap<String,Any>().also { map->
            map["localAddr"] = request.localAddr
            map["localName"] = request.localName
            map["localPort"] = request.localPort
            map["remoteAddr"] = request.remoteAddr
            map["remoteHost"] = request.remoteHost
            map["remotePort"] = request.remotePort
        }
    }


    @RequestMapping("/get/{name}")
    fun getName(@PathVariable("name") name:String) : User {
        return User(name, "123456")
    }


    // HTTP API支持跨域调用
    @CrossOrigin(methods = [RequestMethod.POST, RequestMethod.GET])
    @RequestMapping(path = ["/get/userId/{userId}"])
    fun info(@PathVariable(name = "userId") userId: String?): String? {
        return userId
    }
}