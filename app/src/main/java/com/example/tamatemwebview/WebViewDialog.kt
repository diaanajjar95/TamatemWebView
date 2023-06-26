package com.example.tamatemwebview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.DialogFragment
import com.airbnb.lottie.LottieAnimationView

class WebViewDialog : DialogFragment() {

    private lateinit var webView: WebView
    private lateinit var lottieLoader: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_dialog_fragment_webview, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webView)
        lottieLoader = view.findViewById(R.id.lottieLoader)

        view.findViewById<AppCompatImageView>(R.id.closeImg).setOnClickListener {
            dismissAllowingStateLoss()
        }

        view.findViewById<AppCompatImageView>(R.id.backImg).setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                Toast.makeText(
                    view.context,
                    getString(R.string.go_back_error_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        view.findViewById<AppCompatImageView>(R.id.forewordImg).setOnClickListener {
            if (webView.canGoForward()) {
                webView.goForward()
            } else {
                Toast.makeText(
                    view.context,
                    getString(R.string.go_foreword_error_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        view.findViewById<AppCompatImageView>(R.id.refreshImg).setOnClickListener {
            webView.reload()
        }

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        val url = "https://tamatemplus.com"
        webView.loadUrl(url)

        webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                showLoader()
            }

            override fun onPageFinished(view: WebView, url: String) {
                hideLoader()
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                error?.let {
                    Log.d(TAG, "onReceivedError: errorCode : ${it.errorCode}")
                }
            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                errorResponse?.let {
                    Log.d(TAG, "onReceivedHttpError: statusCode : ${it.statusCode}")
                }
            }
        }
    }

    private fun showLoader() {
        lottieLoader.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        lottieLoader.visibility = View.GONE
    }

    /**
     * responsible to adjust the layout parameters of the dialog.
     * */
    override fun onStart() {
        super.onStart()
        val dialog = dialog
        dialog?.window?.let {
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
    }

    companion object {
        const val TAG = "WebViewDialog"
    }
}