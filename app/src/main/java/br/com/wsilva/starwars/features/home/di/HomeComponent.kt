package br.com.wsilva.starwars.features.home.di

import br.com.wsilva.starwars.di.AppModule
import br.com.wsilva.starwars.features.home.HomeFragment
import dagger.Component

@Component(modules = [AppModule::class, HomeModule::class])
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}