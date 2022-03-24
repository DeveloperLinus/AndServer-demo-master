package com.itlong.andserver.controller

import com.yanzhenjie.andserver.annotation.RequestMapping
import com.yanzhenjie.andserver.annotation.RequestMethod
import com.yanzhenjie.andserver.annotation.RestController


@RestController
@RequestMapping("/request/mapping", method = [RequestMethod.POST])
class RequestMappingController {

    // param 示例，用于规定请求参数
    @RequestMapping(path = ["/params"], params = ["name=123"])
    fun postName() : String{
        return "post one params success"
    }


    // consume示例，对应客户端content-type的类型规定
    @RequestMapping(path = ["/consume"], consumes = ["application/json"])
    fun postConsume() : String{
        return "post one consume success"
    }


    // produce示例，规定客户端的Accept，下面text/plain会作为content-type被发送到客户端
    // 下面客户端的Accept是*/*或者text/plain就可以校验通过
    @RequestMapping(path = ["/produce"], produces = ["text/plain"])
    fun postProduce(): String {
        return "post one produce success"
    }
}