package br.com.wsilva.starwars.di

import br.com.wsilva.starwars.model.dto.PeopleDTO
import br.com.wsilva.starwars.model.repository.*
import br.com.wsilva.starwars.service.RestApi
import br.com.wsilva.starwars.util.AppUtils
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppPeopleRepository {

    val api: RestApi
    val bag: CompositeDisposable
    val schedulers: AppSchedulers
    val peopleRepository: PeopleRepository
    val filmsRepository: FilmsRepository
    val peopleFilmsRepository: PeopleFilmsRepository
    val speciesRepository: SpeciesRepository
    val peopleSpeciesRepository: PeopleSpeciesRepository
    val vehiclesRepository: VehiclesRepository
    val starshipsRepository: StarshipsRepository
    val peopleStarshipsRepository: PeopelStarshipsRepository
    val peopleVehiclesRepository: PeopleVehiclesRepository

    @Inject
    constructor(api: RestApi, bag: CompositeDisposable, schedulers: AppSchedulers,
                peopleRepository: PeopleRepository, filmsRepository: FilmsRepository,
                peopleFilmsRepository: PeopleFilmsRepository, speciesRepository: SpeciesRepository,
                peopleSpeciesRepository: PeopleSpeciesRepository, vehiclesRepository: VehiclesRepository,
                starshipsRepository: StarshipsRepository, peopleStarshipsRepository: PeopelStarshipsRepository,
                peopleVehiclesRepository: PeopleVehiclesRepository) {
        this.api = api
        this.bag = bag
        this.schedulers = schedulers
        this.peopleRepository = peopleRepository
        this.filmsRepository = filmsRepository
        this.peopleFilmsRepository = peopleFilmsRepository
        this.speciesRepository = speciesRepository
        this.peopleSpeciesRepository = peopleSpeciesRepository
        this.vehiclesRepository = vehiclesRepository
        this.starshipsRepository = starshipsRepository
        this.peopleStarshipsRepository = peopleStarshipsRepository
        this.peopleVehiclesRepository = peopleVehiclesRepository
    }

    fun getAllFilms(list: List<PeopleDTO>): Observable<List<PeopleDTO>> {
        return Observable.create<List<PeopleDTO>> {
                list.forEach { peopleDTO ->
                    peopleDTO.films.forEach { url ->
                        val filmsId = AppUtils.extractIdFromURL(url)
                        val call = api.getFilm(filmsId).execute()
                        if (call.isSuccessful) {
                            val filmsDTO = call.body()
                            filmsDTO?.id = filmsId
                            // Save Film
                            filmsRepository.save(filmsDTO!!)
                            // Save People Films
                            peopleFilmsRepository.save(peopleDTO.id, filmsDTO?.id)
                        }
                    }
                }
                it.onNext(list)
                it.onComplete()
            }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }

    fun getAllSpecies(list: List<PeopleDTO>): Observable<List<PeopleDTO>> {
        return Observable.create<List<PeopleDTO>> {
                list.forEach { peopleDTO ->
                    peopleDTO.species.forEach { url ->
                        val speciesId = AppUtils.extractIdFromURL(url)
                        val call = api.getSpecies(speciesId).execute()
                        if (call.isSuccessful) {
                            val speciesDTO = call.body()
                            speciesDTO?.id = speciesId
                            // Save Specie
                            speciesRepository.save(speciesDTO!!)
                            // Save People Specie
                            peopleSpeciesRepository.save(peopleDTO.id, speciesDTO?.id)
                        }
                    }
                }
                it.onNext(list)
                it.onComplete()
            }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }

    fun getAllStarships(list: List<PeopleDTO>): Observable<List<PeopleDTO>> {
        return Observable.create<List<PeopleDTO>> {
            list.forEach { peopleDTO ->
                peopleDTO.starships.forEach { url ->
                    val starshipsId = AppUtils.extractIdFromURL(url)
                    val call = api.getStarships(starshipsId).execute()
                    if (call.isSuccessful) {
                        val starshipsDTO = call.body()
                        starshipsDTO?.id = starshipsId
                        // Save Starship
                        starshipsRepository.save(starshipsDTO!!)
                        // Save Peopel Starship
                        peopleStarshipsRepository.save(peopleDTO.id, starshipsDTO?.id)
                    }
                }
            }
            it.onNext(list)
            it.onComplete()
        }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }

    fun getAllVehicles(list: List<PeopleDTO>): Observable<List<PeopleDTO>> {
        return Observable.create<List<PeopleDTO>> {
            list.forEach { peopleDTO ->
                peopleDTO.starships.forEach { url ->
                    val vehiclesId = AppUtils.extractIdFromURL(url)
                    val call = api.getVehicles(vehiclesId).execute()
                    if (call.isSuccessful) {
                        val vehiclesDTO = call.body()
                        vehiclesDTO?.id = vehiclesId
                        // Save Vehicles
                        vehiclesRepository.save(vehiclesDTO!!)
                        // Save Peop
                        peopleVehiclesRepository.save(peopleDTO.id, vehiclesDTO?.id)
                    }
                }
            }
            it.onNext(list)
            it.onComplete()
        }.observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }
}