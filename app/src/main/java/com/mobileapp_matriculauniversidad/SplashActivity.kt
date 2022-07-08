package com.mobileapp_matriculauniversidad

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.mobileapp_matriculauniversidad.Vista.Login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        val splashTread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(1000)
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                    finish()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                super.run()
            }
        }
        splashTread.start()
    }
}