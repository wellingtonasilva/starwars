package br.com.wsilva.starwars.features.splash

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.di.AppSchedulers
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashPresenter: SplashContract.Presenter {

    val view: SplashContract.View
    val bag: CompositeDisposable
    val schedulers: AppSchedulers

    @Inject
    constructor(view: SplashContract.View, bag: CompositeDisposable,
                schedulers: AppSchedulers
    ) {
        this.view = view
        this.bag = bag
        this.schedulers = schedulers
    }

    override fun startAnimation(context: Context, ship1: android.view.View, ship2: android.view.View,
                                logo: android.view.View) {

        Observable.timer(1, TimeUnit.SECONDS)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe { starAnimationLogo(context, logo) }

        Observable.timer(2, TimeUnit.SECONDS)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe { startAnimationShips(context, ship1, ship2) }

        Observable.timer(6, TimeUnit.SECONDS)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe { view.showPrincipal() }
    }

    override fun onDestroy() {
    }

    override fun onCreate() {
    }

    private fun starAnimationLogo(context: Context, logo: android.view.View) {
        val fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        val fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out)
        logo.startAnimation(fadeIn)
        logo.visibility = View.VISIBLE
        logo.startAnimation(fadeOut)
    }

    private fun startAnimationShips(context: Context, ship1: android.view.View, ship2: android.view.View) {
        ship1.visibility = View.VISIBLE
        ship2.visibility = View.VISIBLE
        SpringAnimation(ship1, DynamicAnimation.TRANSLATION_Y, -800f).apply {
            spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
            spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            start()
        }

        SpringAnimation(ship2, DynamicAnimation.TRANSLATION_Y, 950f).apply {
            spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
            spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            start()
        }
    }
}