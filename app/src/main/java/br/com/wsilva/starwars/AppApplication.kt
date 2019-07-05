package br.com.wsilva.starwars

import android.app.Application
import br.com.wsilva.starwars.di.AppComponent
import br.com.wsilva.starwars.di.AppDatabaseModule
import br.com.wsilva.starwars.di.AppModule
import br.com.wsilva.starwars.di.DaggerAppComponent

class AppApplication: Application() {
    companion object{
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .appDatabaseModule(AppDatabaseModule())
            .build()
    }
}