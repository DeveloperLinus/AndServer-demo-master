package com.itlong.andserver.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.itlong.andserver.R
import com.itlong.andserver.base.HttpService

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(Intent(this, HttpService::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, HttpService::class.java))
    }
}