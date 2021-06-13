package com.reo.trivia.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reo.trivia.R
import com.uber.autodispose.android.lifecycle.autoDispose
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        showWelcomeMessage()
        showWelcomeMessage2()
    }

    /**
     * subscribeする前にActivityのonPauseが呼ばれた場合
     * 2020-05-04 12:26:00.581 2369-2369/com.reo.trivia W/MainActivity: ----onResume----
     * 2020-05-04 12:26:02.159 2369-2369/com.reo.trivia W/MainActivity: showWelcomeMessage Disposing Activity observer from onCreate()
     * 2020-05-04 12:26:02.160 2369-2369/com.reo.trivia W/MainActivity: ----onPause----
     * 2020-05-04 12:26:02.320 2369-2369/com.reo.trivia W/MainActivity: ----onStop----
     */
    private fun showWelcomeMessage() {
        Single.timer(3, TimeUnit.SECONDS)
            .doOnDispose {
                Log.w(
                    TAG,
                    "showWelcomeMessage Disposing Activity observer from onCreate()"
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(this, Lifecycle.Event.ON_PAUSE)
            .subscribe { result ->
                Log.w(TAG, "showWelcomeMessage result: $result")
                Toast.makeText(this, "Trivia are randomly selected (❁´ω`❁)", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    /**
     * subscribeされる前にActivityが破棄された場合のログ
     * 2020-05-04 12:14:42.794 1098-1098/com.reo.trivia W/MainActivity: ----onResume----
     * 2020-05-04 12:14:44.137 1098-1098/com.reo.trivia W/MainActivity: ----onPause----
     * 2020-05-04 12:14:44.978 1098-1098/com.reo.trivia W/MainActivity: ----onStop----
     * 2020-05-04 12:14:44.994 1098-1098/com.reo.trivia W/MainActivity: showWelcomeMessage2 Disposing Activity observer from onCreate()
     * 2020-05-04 12:14:45.234 1098-1098/com.reo.trivia W/MainActivity: ----onDestroy----
     */
    private fun showWelcomeMessage2() {
        Single.timer(10, TimeUnit.SECONDS)
            .doOnDispose {
                Log.w(
                    TAG,
                    "showWelcomeMessage2 Disposing Activity observer from onCreate()"
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(this)
            .subscribe { result ->
                Log.w(TAG, "showWelcomeMessage2 result: $result")
                Toast.makeText(this, "Welcome Trivia Application (≧▽≦)", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG, "----onResume----")
    }

    override fun onPause() {
        super.onPause()
        Log.w(TAG, "----onPause----")
    }

    override fun onStop() {
        super.onStop()
        Log.w(TAG, "----onStop----")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w(TAG, "----onDestroy----")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
