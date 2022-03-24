package com.itlong.andserver.controller

import android.util.Log
import com.itlong.andserver.User
import com.itlong.andserver.utils.JsonUtil
import com.yanzhenjie.andserver.annotation.FormPart
import com.yanzhenjie.andserver.annotation.PostMapping
import com.yanzhenjie.andserver.annotation.RequestMapping
import com.yanzhenjie.andserver.annotation.RestController

// FormPart和RequestParam和QueryParam一样只能用在方法参数上，用来获取客户端的请求参数，不同的是FormPart仅用来获取 Form 中的参数
// FormPart支持的参数类型有两种，一种是MultipartFile，另一种是 Model，比如User、List<User>和Map<String, User>这样的参数。
// 例如客户端在表单的某个 Item 提交了一段 JSON、或者一个 JSON 文件，当我们使用RequestParam获取到这段 JSON 以后，还需要把这段 JSON 转为 Model 对象，这样显得比较麻烦。如果使用了FormPart就可以把这样的复杂参数转换为服务端的 Model 对象。

@RestController
@RequestMapping("/FormPart")
class FormPartController {
    // i don't know how to test this case by postman
    @PostMapping("/user/update")
    fun updateUser(@FormPart("user") user: User?): User? {
        Log.d("AndServer", "update, account->${user?.account} and password->${user?.password}")
        return user
    }

    // i don't know how to test this case by postman
    @PostMapping("/users/update")
    fun updateUserList(@FormPart("userList") userList: List<User>): List<User> {
        for (user in userList) {
            Log.d("AndServer", "update, account->${user.account} and password->${user.password}")
        }
        return userList
    }
}