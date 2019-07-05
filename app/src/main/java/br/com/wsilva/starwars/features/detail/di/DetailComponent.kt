package br.com.wsilva.starwars.features.detail.di

import br.com.wsilva.starwars.di.AppDatabaseModule
import br.com.wsilva.starwars.di.AppModule
import br.com.wsilva.starwars.features.detail.DetailFragment
import dagger.Component

@Component(modules = [AppModule::class, AppDatabaseModule::class, DetailModule::class])
interface DetailComponent {
    fun inject(fragment: DetailFragment)
}