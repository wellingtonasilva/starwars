package br.com.wsilva.starwars.features.splash

import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.FlingAnimation
import android.support.animation.SpringAnimation
import android.widget.ImageView
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.core.BasicActivity
import kotlinx.android.synthetic.main.lay_splash_activity.*

class SplashActivity: BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_splash_activity)

        button.setOnClickListener {
            val nave = findViewById<ImageView>(R.id.imgNave)
            //val springAnim = findViewById<View>(R.id.imageView).let { img ->
                // Setting up a spring animation to animate the view’s translationY property with the final
                // spring position at 0.
                SpringAnimation(nave, DynamicAnimation.TRANSLATION_Y, 0f)
            //}
        }
    }
}