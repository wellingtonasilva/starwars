package br.com.wsilva.starwars.features.splash.di

import br.com.wsilva.starwars.di.AppPeopleRepository
import br.com.wsilva.starwars.di.AppSchedulers
import br.com.wsilva.starwars.features.splash.SplashContract
import br.com.wsilva.starwars.features.splash.SplashPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class SplashModule(private val view: SplashContract.View) {

    @Provides
    fun providesSplashView(): SplashContract.View = view

    @Provides
    fun providesSplashPresenter(view: SplashContract.View, bag: CompositeDisposable,
                                schedulers: AppSchedulers, repository: AppPeopleRepository): SplashContract.Presenter {
        return SplashPresenter(view, bag, schedulers, repository)
    }
}