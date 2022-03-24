package com.itlong.andserver.base

import android.content.Context
import com.itlong.andserver.utils.FileUtil
import com.yanzhenjie.andserver.annotation.Config
import com.yanzhenjie.andserver.framework.config.Multipart
import com.yanzhenjie.andserver.framework.config.WebConfig
import com.yanzhenjie.andserver.framework.website.AssetsWebsite
import com.yanzhenjie.andserver.framework.website.StorageWebsite

@Config
class AppConfig : WebConfig {
    override fun onConfig(context: Context, delegate: WebConfig.Delegate) {
        // 增加一个位于assert的Web目录网站
        delegate.addWebsite(AssetsWebsite(context, "/web/"))

        // 增加一个位于/sdcard/Download/AndServer/目录的网站
        delegate.addWebsite(StorageWebsite("/sdcard/Download/AndServer/"))

        val uploadDir = FileUtil.getFile("sdcard/")

        delegate.setMultipart(Multipart.newBuilder()
            .allFileMaxSize(1024 * 1024 * 20) // 单个请求所有文件总大小
            .fileMaxSize(1024 * 1024 * 5) // 单个请求每个文件大小
            .maxInMemorySize(1024 * 10) // 内存缓存大小
            .uploadTempDir(uploadDir) // 上传文件保存目录
            .build())
    }
}