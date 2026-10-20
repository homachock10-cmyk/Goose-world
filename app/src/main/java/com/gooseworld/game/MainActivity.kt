package com.gooseworld.game

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : Activity() {
    private lateinit var gameView: WebView

    @Suppress("DEPRECATION")
    private fun immersiveMode() {
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        immersiveMode()
        gameView = WebView(this)
        gameView.setBackgroundColor(android.graphics.Color.rgb(137, 201, 118))
        gameView.settings.javaScriptEnabled = true
        gameView.settings.domStorageEnabled = true
        gameView.settings.allowFileAccess = true
        gameView.webViewClient = WebViewClient()
        gameView.webChromeClient = WebChromeClient()
        setContentView(gameView)
        if (savedInstanceState == null) {
            gameView.loadUrl("file:///android_asset/game/index.html")
        } else {
            gameView.restoreState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        gameView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    @Suppress("DEPRECATION")
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) immersiveMode()
    }

    override fun onDestroy() {
        gameView.destroy()
        super.onDestroy()
    }
}
