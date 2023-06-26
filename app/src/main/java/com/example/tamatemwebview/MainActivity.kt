package com.example.tamatemwebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.openBrowserBtn).setOnClickListener {
            showWebViewDialog()
        }

    }

    private fun showWebViewDialog() {
        val webViewDialog = WebViewDialog()
        webViewDialog.show(supportFragmentManager, WebViewDialog.TAG)
    }

}