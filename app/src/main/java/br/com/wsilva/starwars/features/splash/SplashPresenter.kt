package br.com.wsilva.starwars.features.splash

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.di.AppPeopleRepository
import br.com.wsilva.starwars.di.AppSchedulers
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashPresenter: SplashContract.Presenter {

    val view: SplashContract.View
    val bag: CompositeDisposable
    val schedulers: AppSchedulers
    val repository: AppPeopleRepository

    @Inject
    constructor(view: SplashContract.View, bag: CompositeDisposable,
                schedulers: AppSchedulers, repository: AppPeopleRepository
    ) {
        this.view = view
        this.bag = bag
        this.schedulers = schedulers
        this.repository = repository
    }

    override fun startAnimation(context: Context, ship1: android.view.View, ship2: android.view.View,
                                logo: android.view.View) {

        bag.add(
            Observable
                .timer(2, TimeUnit.SECONDS)
                .repeat(20)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe { starAnimationLogo(context, logo) }
        )

        bag.add(
            Observable
                .timer(1, TimeUnit.SECONDS)
                .repeat(10)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe { startAnimationShips(context, ship1, ship2) }
        )
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
            setStartVelocity(2F)
            start()
        }
    }

    override fun loadAllPeople() {
            repository.
                loadAllPeople()
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { view.showPrincipal()}
    }
}