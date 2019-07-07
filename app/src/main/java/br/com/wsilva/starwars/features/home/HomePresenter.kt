package br.com.wsilva.starwars.features.home

import br.com.wsilva.starwars.di.AppPeopleRepository
import br.com.wsilva.starwars.di.AppSchedulers
import br.com.wsilva.starwars.model.dto.PeopleDTO
import br.com.wsilva.starwars.service.RestApi
import br.com.wsilva.starwars.util.AppUtils
import io.reactivex.Observable
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
        bag.add(
            Observable.create<List<PeopleDTO>> {
                val list = mutableListOf<PeopleDTO>()
                var page = 0
                var completed = false
                while (!completed) {
                    val call = api.getAllPeople(++page).execute()
                    if (call.isSuccessful) {
                        call.body()?.results?.forEach {people ->
                            people.id = AppUtils.extractIdFromURL(people.url)
                            list.add(people)
                        }
                        completed = call.body()?.next == null
                    } else {
                        completed = true
                    }
                }
                it.onNext(list)
                it.onComplete()
            }   .map { repository.peopleRepository.save(it) }
                .flatMap { repository.getAllSpecies(it) }
                .flatMap { repository.getAllFilms(it) }
                .flatMap { repository.getAllStarships(it) }
                .flatMap { repository.getAllVehicles(it) }
                .doAfterTerminate { afterLoadAllPeople() }
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe()
        )
    }

    override fun afterLoadAllPeople() {
        repository.peopleRepository
            .listAll()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .toObservable()
    }
}