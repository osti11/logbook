package com.ema.jannik.logbook.activity

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import com.ema.jannik.logbook.R
import gr.net.maroulis.library.EasySplashScreen

/**
 * This activity is for an splash screen.
 */
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = EasySplashScreen(this@SplashScreenActivity)
                .withFullScreen()
                .withTargetActivity(MainActivity::class.java)
                .withSplashTimeOut(2000)
                .withBackgroundColor(Color.WHITE)
                //.withBeforeLogoText(getString(R.string.app_name))
                .withLogo(R.drawable.ic_launcher_round)
            .withFooterText ("developed by Jannik Ostermayer") //TODO string.xml

        val splashScreen: View = config.create()    //create View

        setContentView(splashScreen)    //set layout for activity
    }
}
