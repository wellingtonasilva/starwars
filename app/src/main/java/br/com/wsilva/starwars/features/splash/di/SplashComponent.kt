package br.com.wsilva.starwars.features.splash.di

import br.com.wsilva.starwars.di.AppDatabaseModule
import br.com.wsilva.starwars.di.AppModule
import br.com.wsilva.starwars.features.splash.SplashFragment
import dagger.Component

@Component(modules = [SplashModule::class, AppModule::class, AppDatabaseModule::class])
interface SplashComponent {
    fun inject(fragment: SplashFragment)
}