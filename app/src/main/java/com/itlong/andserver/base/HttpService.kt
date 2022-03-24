package com.itlong.andserver.base

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.yanzhenjie.andserver.AndServer
import com.yanzhenjie.andserver.Server
import java.lang.Exception
import java.util.concurrent.TimeUnit

class HttpService : Service(), Server.ServerListener {
    companion object{
        private const val TAG = "HttpService"
    }
    private val server by lazy {
        AndServer.webServer(this)
            .port(8891)
            .listener(this)
            .timeout(10, TimeUnit.SECONDS)
            .build()
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate: ")
        server.startup()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
        server.shutdown()
    }

    override fun onException(e: Exception?) {
        Log.i(TAG, "onException: ")
    }

    override fun onStarted() {
        Log.i(TAG, "onStarted: ")
    }

    override fun onStopped() {
        Log.i(TAG, "onStopped: ")
    }
}