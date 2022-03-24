package com.itlong.andserver.controller

import com.yanzhenjie.andserver.annotation.Controller
import com.yanzhenjie.andserver.annotation.GetMapping
import com.yanzhenjie.andserver.framework.body.FileBody
import com.yanzhenjie.andserver.framework.body.JsonBody
import com.yanzhenjie.andserver.framework.body.StringBody
import com.yanzhenjie.andserver.http.ResponseBody
import org.json.JSONObject
import java.io.File

// 事实上，以上示例中的返回值，最后都被包装为ResponseBody后发送出去，因此如果开发者返回ResponseBody将被直接发送到客户端而不经过MessageConverter，
// 因此我们可以直接返回ResponseBody
@Controller
class ProjectController {
    // fail Server internal error, The return value of [com.yanzhenjie.andserver.framework.body.StringBody@d74dde5] is not supported.
    @GetMapping("/info1")
    fun getInfo() : StringBody {
        return StringBody("1111222")
    }

    // fail Server internal error, The return value of [com.yanzhenjie.andserver.framework.body.FileBody@a7c70ba] is not supported.
    @GetMapping("/file")
    fun getFile():FileBody {
        return FileBody(File("D:\\Linus\\copy.cpp"))
    }

    // fail Server internal error, The return value of [com.yanzhenjie.andserver.framework.body.JsonBody@203f94f] is not supported.
    @GetMapping("/info2")
    fun getInfo2(): ResponseBody {
        val user =  JSONObject().also {
            it.put("huqinghui", "123456")
            it.put("linzelong", "aaaaaa")
        }
        return JsonBody(user)
    }
}