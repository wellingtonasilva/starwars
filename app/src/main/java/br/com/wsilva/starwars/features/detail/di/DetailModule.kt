package br.com.wsilva.starwars.features.detail.di

import br.com.wsilva.starwars.di.AppPeopleRepository
import br.com.wsilva.starwars.di.AppSchedulers
import br.com.wsilva.starwars.features.detail.DetailContract
import br.com.wsilva.starwars.features.detail.DetailPresenter
import br.com.wsilva.starwars.model.repository.*
import br.com.wsilva.starwars.service.RestApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class DetailModule(private val view: DetailContract.View) {

    @Provides
    fun providesDetailView(): DetailContract.View = view

    @Provides
    fun providesDetailPresenter(view: DetailContract.View, api: RestApi, bag: CompositeDisposable,
                                schedulers: AppSchedulers, appPeopleRepository: AppPeopleRepository
                                ): DetailContract.Presenter {
        return DetailPresenter(view, api, bag, schedulers, appPeopleRepository)
    }
}