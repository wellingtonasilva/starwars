package br.com.wsilva.starwars.features.detail

import br.com.wsilva.starwars.di.AppPeopleRepository
import br.com.wsilva.starwars.di.AppSchedulers
import br.com.wsilva.starwars.service.RestApi
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailPresenter: DetailContract.Presenter {

    val view: DetailContract.View
    val api: RestApi
    val bag: CompositeDisposable
    val schedulers: AppSchedulers
    val repository: AppPeopleRepository
    var peopleId = 0L
    var peopleName = ""

    @Inject
    constructor(view: DetailContract.View, api: RestApi, bag: CompositeDisposable, schedulers: AppSchedulers,
                repository: AppPeopleRepository) {
        this.view = view
        this.api = api
        this.bag = bag
        this.schedulers = schedulers
        this.repository = repository
    }

    override fun onDestroy() {
    }

    override fun onCreate() {
    }

    override fun loadPerson(id: Long) {

    }

    fun loadVehicles(peopleId: Long) {
        bag.add(
            repository.vehiclesRepository
                .listAllByPeopleId(peopleId)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { view.showVehicles(it) }
        )
    }
}