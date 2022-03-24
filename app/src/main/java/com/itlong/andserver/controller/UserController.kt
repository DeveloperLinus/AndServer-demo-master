package com.itlong.andserver.controller

import android.util.Log
import com.itlong.andserver.User
import com.itlong.andserver.utils.JsonUtil
import com.yanzhenjie.andserver.annotation.*
import com.yanzhenjie.andserver.framework.body.JsonBody
import com.yanzhenjie.andserver.http.HttpRequest
import org.json.JSONObject


// 添加了RestController注解的类中的方法拥有将返回值直接输出到客户端的能力
@RestController
class UserController {
    @PostMapping("/login")
    fun login(@RequestParam("account") account:String, @RequestParam("password")password:String): JsonBody {
        Log.d("Http", "login account->$account, password->$password")
        return JsonBody(JsonUtil.toJsonString(User(account, password)))
    }

    // pass {"account":"huqinghui","password":"123456"}
    @GetMapping("/getUserJson")
    fun getUserJson() : String? {
        return JsonUtil.toJsonString(User("huqinghui", "123456"))
    }

    // pass {"account":"huqinghui","password":"123456"}
    @GetMapping("/user/get")
    fun getUserJSONObject(): JSONObject {
        return JSONObject().also {
            it.put("account", "huqinghui")
            it.put("password", "123456")
        }
    }

    @ResponseBody
    @GetMapping("/project/info")
    fun newInfo(): String {
        return "I am new api."
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


    // User(account=huqh, password=123456)
    @GetMapping("/get/{name}")
    fun getUser(@PathVariable("name") name:String) : User {
        return User(name, "123456")
    }


    // [User(account=huqinghui, password=123456), User(account=linlejian, password=aaaaaa), User(account=linzelong, password=bbbbb)]
    @GetMapping("/userList/get")
    fun getUserList(): List<User> {
        return ArrayList<User>().also { userList->
            userList.add(User("huqinghui", "123456"))
            userList.add(User("linlejian", "aaaaaa"))
            userList.add(User("linzelong", "bbbbb"))
        }
    }
    // HTTP API支持跨域调用
    @CrossOrigin(methods = [RequestMethod.POST, RequestMethod.GET])
    @RequestMapping(path = ["/get/userId/{userId}"])
    fun info(@PathVariable(name = "userId") userId: String?): String? {
        return userId
    }

    @RequestMapping(path = ["/register"], method = [RequestMethod.GET, RequestMethod.POST])
    fun register() : String {
        return "register success"
    }
}