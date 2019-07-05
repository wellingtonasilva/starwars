package br.com.wsilva.starwars.features.home

import br.com.wsilva.starwars.di.AppSchedulers
import br.com.wsilva.starwars.model.dto.PeopleDTO
import br.com.wsilva.starwars.service.RestApi
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter: HomeContract.Presenter {

    private val view: HomeContract.View
    private val api: RestApi
    private val bag: CompositeDisposable
    private val schedulers: AppSchedulers

    @Inject
    constructor(view: HomeContract.View, api: RestApi, bag: CompositeDisposable, schedulers: AppSchedulers) {
        this.view = view
        this.api = api
        this.bag = bag
        this.schedulers = schedulers
    }

    override fun onDestroy() {
        bag.clear()
    }

    override fun onCreate() {
    }

    override fun loadAllPeople() {
        bag.add(
            Observable.create<List<PeopleDTO>> {
                val list = mutableListOf<PeopleDTO>()
                var page = 0
                var completed = false
                while (!completed) {
                    val call = api.getAllPeople(++page).execute()
                    if (call.isSuccessful) {
                        call.body()?.results?.forEach {people -> list.add(people)}
                        //completed = call.body()?.next == null
                        completed = true
                    } else {
                        completed = true
                    }
                }
                it.onNext(list)
                it.onComplete()
            }.observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { result -> afterLoadAllPeople(result) }
        )
    }

    override fun afterLoadAllPeople(result: List<PeopleDTO>) {
        view.showPeople(result)
    }
}