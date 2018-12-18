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
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.WHITE)
                .withAfterLogoText(getString(R.string.app_name))
                .withLogo(R.drawable.ic_splash_screen)
            .withFooterText("developed by Jannik Ostermayer") //TODO string.xml

        TextViewCompat.setTextAppearance(config.afterLogoTextView, R.style.TextAppearance_MaterialComponents_Headline3)
        config.afterLogoTextView.setTextColor(ContextCompat.getColor(applicationContext ,R.color.colorPrimaryDark))
        config.logo.scaleType = ImageView.ScaleType.CENTER_INSIDE


        val splashScreen: View = config.create()

        setContentView(splashScreen)    //set layout for activity
    }
}
