package com.reo.trivia.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.reo.trivia.R

class AppLinkActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_link)
    }

    // [START handle_app_link]
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val action = intent.action
        val data = intent.data
        if (Intent.ACTION_VIEW == action && data != null) {
            Toast.makeText(
                this,
                "articleId: " + data.lastPathSegment + " linkText: " + data.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }
    // [END handle_app_link]
}
