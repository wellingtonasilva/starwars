package br.com.wsilva.starwars.features.detail

import android.util.Log
import br.com.wsilva.starwars.di.AppPeopleRepository
import br.com.wsilva.starwars.di.AppSchedulers
import br.com.wsilva.starwars.model.dto.PeopleDTO
import br.com.wsilva.starwars.model.repository.*
import br.com.wsilva.starwars.service.RestApi
import br.com.wsilva.starwars.util.AppUtils
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailPresenter: DetailContract.Presenter {

    val view: DetailContract.View
    val api: RestApi
    val bag: CompositeDisposable
    val schedulers: AppSchedulers
    val repository: AppPeopleRepository
    var personId = 0L

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
        bag.add(
            api.getPeople(id)
                .map { savePeople(id, it)}
                .flatMap{ peopleDTO -> getAllFilms(id, peopleDTO) }
                .flatMap { peopleDTO -> getAllSpecies(id, peopleDTO) }
                .flatMap { peopleDTO -> getAllStarships(id, peopleDTO) }
                .flatMap { peopleDTO -> getAllVehicles(id, peopleDTO) }
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { it -> Log.d("### ", it.name) }
        )
    }

    fun savePeople(id: Long, peopleDTO: PeopleDTO): PeopleDTO {
        return repository.peopleRepository.save(id, peopleDTO)
    }

    fun getAllFilms(peopleId: Long, peopleDTO: PeopleDTO): Observable<PeopleDTO> {
        return Observable.create<String> { it ->
            peopleDTO.films.forEach { item -> it.onNext(item) }
            it.onComplete()
        }.flatMap { url -> api.getFilm(AppUtils.extractIdFromURL(url)) }
            .flatMap { ObservableSource<Long> { e -> e.onNext(repository.filmsRepository.save(AppUtils.extractIdFromURL(it.url), it))}}
            .flatMap { filmId ->  ObservableSource<Boolean> { e -> e.onNext(repository.peopleFilmsRepository.save(peopleId, filmId))}}
            .flatMap { ObservableSource<PeopleDTO> { e -> e.onNext(peopleDTO) } }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())

    }

    fun getAllSpecies(peopleId: Long, peopleDTO: PeopleDTO): Observable<PeopleDTO> {
        return Observable.create<String> { it ->
            peopleDTO.species.forEach { item -> it.onNext(item) }
            it.onComplete()
        }.flatMap { url -> api.getSpecies(AppUtils.extractIdFromURL(url))}
            .flatMap { ObservableSource<Long> { e -> e.onNext(repository.speciesRepository.save(AppUtils.extractIdFromURL(it.url), it))}}
            .flatMap { speciesId -> ObservableSource<Boolean> { e -> e.onNext(repository.peopleSpeciesRepository.save(peopleId, speciesId)) } }
            .flatMap { ObservableSource<PeopleDTO> { e -> e.onNext(peopleDTO) } }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }

    fun getAllStarships(peopleId: Long, peopleDTO: PeopleDTO): Observable<PeopleDTO> {
        return Observable.create<String> { it ->
            peopleDTO.starships.forEach { item -> it.onNext(item) }
            it.onComplete()
        }.flatMap { url -> api.getStarships(AppUtils.extractIdFromURL(url))}
            .flatMap { ObservableSource<Long> { e -> e.onNext(repository.starshipsRepository.save(AppUtils.extractIdFromURL(it.url), it))}}
            .flatMap { starshipsId -> ObservableSource<Boolean> { e -> e.onNext(repository.peopleStarshipsRepository.save(peopleId, starshipsId))} }
            .flatMap { ObservableSource<PeopleDTO> { e -> e.onNext(peopleDTO) } }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }

    fun getAllVehicles(peopleId: Long, peopleDTO: PeopleDTO): Observable<PeopleDTO> {
        return Observable.create<String> { it ->
            peopleDTO.vehicles.forEach { item -> it.onNext(item) }
            it.onComplete()
        }.flatMap { url -> api.getVehicles(AppUtils.extractIdFromURL(url)) }
            .flatMap { ObservableSource<Long> { e -> e.onNext(repository.vehiclesRepository.save(AppUtils.extractIdFromURL(it.url), it))}}
            .flatMap { vehiclesId -> ObservableSource<Boolean> { e -> e.onNext(repository.peopleVehiclesRepository.save(peopleId, vehiclesId))} }
            .flatMap { ObservableSource<PeopleDTO> { e -> e.onNext(peopleDTO) } }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }
}