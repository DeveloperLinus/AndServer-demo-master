package com.itlong.andserver.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.itlong.andserver.R
import com.itlong.andserver.base.HttpService
import com.itlong.andserver.base.ServerManager

class MainActivity : Activity() {
    private val serverManager by lazy {
        ServerManager(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(Intent(this, HttpService::class.java))
//        serverManager.startServer()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, HttpService::class.java))
//        serverManager.stopServer(
    }
}