package br.com.wsilva.starwars.features.home

import br.com.wsilva.starwars.di.AppPeopleRepository
import br.com.wsilva.starwars.di.AppSchedulers
import br.com.wsilva.starwars.service.RestApi
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter: HomeContract.Presenter {

    private val view: HomeContract.View
    private val api: RestApi
    private val bag: CompositeDisposable
    private val schedulers: AppSchedulers
    private val repository: AppPeopleRepository

    @Inject
    constructor(view: HomeContract.View, api: RestApi, bag: CompositeDisposable, schedulers: AppSchedulers,
                repository: AppPeopleRepository) {
        this.view = view
        this.api = api
        this.bag = bag
        this.schedulers = schedulers
        this.repository = repository
    }

    override fun onDestroy() {
        bag.clear()
    }

    override fun onCreate() {
    }

    override fun loadAllPeople() {
        repository.peopleRepository
            .listAll()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe { view.showPeople(it) }
    }
}