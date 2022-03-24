package com.itlong.andserver.base

import android.content.Context
import android.util.Log
import com.yanzhenjie.andserver.AndServer
import com.yanzhenjie.andserver.Server
import java.lang.Exception
import java.util.concurrent.TimeUnit

class ServerManager(context: Context) {
    private val server by lazy {
        AndServer.webServer(context)
            .timeout(10, TimeUnit.SECONDS)
            .listener(object : Server.ServerListener {
                override fun onException(e: Exception) {
                    Log.e("AndServer", "listener exception:${e.message}")
                }

                override fun onStarted() {
                    Log.d("AndServer", "listener server was started")
                    startProxyServer()
                }

                override fun onStopped() {
                    Log.d("AndServer", "listener server was stopped")
                    stopProxyServer()
                }
            }).build()
    }

    // fail ,java.net.BindException: Permission denied
    private val proxyServer by lazy {
        AndServer.proxyServer()
            .addProxy("www.example.com", "http://192.168.133.37:8891")
            .port(80)
            .listener(object : Server.ServerListener {
                override fun onException(e: Exception) {
                    Log.e("AndServer", "listener exception:${e.message}")
                }

                override fun onStarted() {
                    Log.d("AndServer", "listener proxy server was started")
                }

                override fun onStopped() {
                    Log.d("AndServer", "listener proxy listener was stopped")
                }
            })
            .timeout(10, TimeUnit.SECONDS)
            .build()
    }

    fun startProxyServer() {
        if(proxyServer.isRunning) {
            Log.d("AndServer", "the proxy server is running")
        } else {
            Log.d("AndServer", "the proxy server try startup")
            proxyServer.startup()
        }
    }

    fun stopProxyServer() {
        if (proxyServer.isRunning) {
            Log.d("AndServer", "the proxy server try shutdown")
            proxyServer.shutdown()
        } else {
            Log.d("AndServer", "the proxy server is not running")
        }
    }

    fun startServer() {
        if (server.isRunning) {
            Log.d("AndServer", "the server is running")
        } else {
            server.startup()
        }
    }

    fun stopServer() {
        if (server.isRunning) {
            server.shutdown()
        } else {
            Log.d("AndServer", "the server is not running")
        }
    }
}