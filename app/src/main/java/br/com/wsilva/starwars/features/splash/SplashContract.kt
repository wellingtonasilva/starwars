package br.com.wsilva.starwars.features.splash

import android.content.Context
import br.com.wsilva.starwars.core.BasicPresenter

interface SplashContract {

    interface View {
        fun showPrincipal()
    }

    interface Presenter: BasicPresenter {
        fun startAnimation(context: Context, ship1: android.view.View,
                           ship2: android.view.View, logo: android.view.View)
    }
}