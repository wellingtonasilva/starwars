package br.com.wsilva.starwars.features.splash

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.Window
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.core.BasicActivity

class SplashActivity: BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.lay_splash_activity)

        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE)

        init(savedInstanceState)
//        findViewById<View>(R.id.button)!!.setOnClickListener {
//            val nave = findViewById<View>(R.id.imageView)
//            SpringAnimation(nave, DynamicAnimation.TRANSLATION_Y, -800f).apply {
//                spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
//                spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
//                start()
//            }
//        }
    }

    private fun init(savedInstanceState: Bundle?)
    {
        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentByTag(SplashFragment.TAG)
        if (fragment == null) {
            fragment = SplashFragment.newInstance()
        }
        if (savedInstanceState == null) {
            fragment.arguments = intent.extras
        } else {
            fragment.arguments = savedInstanceState
        }
        addFragmentToActivity(fragmentManager, fragment, R.id.frameLayout, SplashFragment.TAG)
    }
}