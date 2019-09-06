package com.example.myapplication.T12_WebView

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    inner class MyWebViewClient: WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            loadingProgressBar.visibility = View.VISIBLE

        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            loadingProgressBar.visibility = View.GONE

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        loadingProgressBar.visibility = View.GONE
        myWebView.webViewClient = MyWebViewClient()
        myWebView.settings.javaScriptEnabled = true

        myWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                loadingProgressBar.progress = newProgress
            }
        }

        myWebView.loadUrl("http://daum.net")

    }

    override fun onBackPressed() {
        if(myWebView.canGoBack()) {
            myWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
