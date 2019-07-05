package br.com.wsilva.starwars.features.home.di

import br.com.wsilva.starwars.di.AppSchedulers
import br.com.wsilva.starwars.features.home.HomeContract
import br.com.wsilva.starwars.features.home.HomePresenter
import br.com.wsilva.starwars.service.RestApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class HomeModule(private val view: HomeContract.View) {

    @Provides
    fun providesHomeView(): HomeContract.View = view

    @Provides
    fun providesHomePresenter(view: HomeContract.View, api: RestApi, bag: CompositeDisposable,
                              schedulers: AppSchedulers): HomeContract.Presenter {
        return HomePresenter(view, api, bag, schedulers)
    }
}