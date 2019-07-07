package br.com.wsilva.starwars.di

import br.com.wsilva.starwars.model.dto.PeopleDTO
import br.com.wsilva.starwars.model.entity.PeopleEntity
import br.com.wsilva.starwars.model.repository.*
import br.com.wsilva.starwars.service.RestApi
import br.com.wsilva.starwars.util.AppUtils
import io.reactivex.Observable
import io.reactivex.Single
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
    val planetsRepository: PlanetsRepository
    val peoplePlanetsRepository: PeoplePlanetsRepository

    @Inject
    constructor(api: RestApi, bag: CompositeDisposable, schedulers: AppSchedulers,
                peopleRepository: PeopleRepository, filmsRepository: FilmsRepository,
                peopleFilmsRepository: PeopleFilmsRepository, speciesRepository: SpeciesRepository,
                peopleSpeciesRepository: PeopleSpeciesRepository, vehiclesRepository: VehiclesRepository,
                starshipsRepository: StarshipsRepository, peopleStarshipsRepository: PeopelStarshipsRepository,
                peopleVehiclesRepository: PeopleVehiclesRepository, planetsRepository: PlanetsRepository,
                peoplePlanetsRepository: PeoplePlanetsRepository) {
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
        this.planetsRepository = planetsRepository
        this.peoplePlanetsRepository = peoplePlanetsRepository
    }

    fun getPeople(peopleId: Long): Observable<PeopleEntity> {
        return api
            .getPeople(peopleId)
            .flatMap { getPeopleWithId(it) }
            .flatMap { getAllVehicles(listOf(it)) }
            .flatMap { getAllStarships(it) }
            .flatMap { getAllFilms(it) }
            .flatMap { getAllPlanets(it) }
            .flatMap { getPeopleEntity(peopleId) }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }

    fun loadAllPeople(): Observable<List<PeopleDTO>> {
        return Observable.create<List<PeopleDTO>> {
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
            }   .map { peopleRepository.save(it) }
                .flatMap { getAllSpecies(it) }
                .flatMap { getAllPlanets(it) }
                .flatMap { getAllFilms(it) }
                .flatMap { getAllStarships(it) }
                .flatMap { getAllVehicles(it) }
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())

    }

    fun getAllFilms(list: List<PeopleDTO>): Observable<List<PeopleDTO>> {
        return Observable.create<List<PeopleDTO>> {
                list.forEach { peopleDTO ->
                    peopleDTO.films.forEach { url ->
                        val filmsId = AppUtils.extractIdFromURL(url)
                        if (!filmsRepository.exist(filmsId)) {
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
                }
                it.onNext(list)
            }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }

    fun getAllSpecies(list: List<PeopleDTO>): Observable<List<PeopleDTO>> {
        return Observable.create<List<PeopleDTO>> {
                list.forEach { peopleDTO ->
                    peopleDTO.species.forEach { url ->
                        val speciesId = AppUtils.extractIdFromURL(url)
                        if (!speciesRepository.exist(speciesId)) {
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
                        // Update specie name in Peole
                        peopleRepository.updateSpecieName(peopleDTO.id, speciesRepository.get(speciesId).name)
                    }
                }
                it.onNext(list)
            }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }

    fun getAllStarships(list: List<PeopleDTO>): Observable<List<PeopleDTO>> {
        return Observable.create<List<PeopleDTO>> {
            list.forEach { peopleDTO ->
                peopleDTO.starships.forEach { url ->
                    val starshipsId = AppUtils.extractIdFromURL(url)
                    if (!starshipsRepository.exist(starshipsId)) {
                        val call = api.getStarships(starshipsId).execute()
                        if (call.isSuccessful) {
                            val starshipsDTO = call.body()
                            starshipsDTO?.id = starshipsId
                            // Save Starship
                            starshipsRepository.save(starshipsDTO!!)
                        }
                    }
                    // Save Peopel Starship
                    peopleStarshipsRepository.save(peopleDTO.id, starshipsId)
                }
            }
            it.onNext(list)
        }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }

    fun getAllVehicles(list: List<PeopleDTO>): Observable<List<PeopleDTO>> {
        return Observable.create<List<PeopleDTO>> {
            list.forEach { peopleDTO ->
                peopleDTO.vehicles.forEach { url ->
                    val vehiclesId = AppUtils.extractIdFromURL(url)
                    if (!vehiclesRepository.exist(vehiclesId)) {
                        val call = api.getVehicles(vehiclesId).execute()
                        if (call.isSuccessful) {
                            val vehiclesDTO = call.body()
                            vehiclesDTO?.id = vehiclesId
                            // Save Vehicles
                            vehiclesRepository.save(vehiclesDTO!!)
                        }
                    }
                    // Save Peop
                    peopleVehiclesRepository.save(peopleDTO.id, vehiclesId)
                }
            }
            it.onNext(list)
        }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }

    fun getAllPlanets(list: List<PeopleDTO>): Observable<List<PeopleDTO>> {
        return Observable.create<List<PeopleDTO>> {
            list.forEach { peopleDTO ->
                peopleDTO.homeWorldUrl.let { url ->
                    val planetId = AppUtils.extractIdFromURL(url)
                    if (!planetsRepository.exist(planetId)) {
                        val call = api.getPlanets(planetId).execute()
                        if (call.isSuccessful) {
                            val planetsDTO = call.body()
                            planetsDTO?.id = planetId
                            // Save Vehicles
                            planetsRepository.save(planetsDTO!!)
                        }
                    }
                    // Save Peop
                    peoplePlanetsRepository.save(peopleDTO.id, planetId)
                    peopleRepository.updateHomeland(peopleDTO.id, planetsRepository.get(planetId).name)
                }
            }
            it.onNext(list)
        }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }

    fun getPeopleEntity(peopleId: Long): Observable<PeopleEntity> {
        return Observable.create<PeopleEntity> { it ->
            it.onNext(peopleRepository.get(peopleId))
        }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }

    fun getPeopleWithId(peopleDTO: PeopleDTO): Observable<PeopleDTO> {
        return Observable.create<PeopleDTO> { it ->
            peopleDTO.id = AppUtils.extractIdFromURL(peopleDTO.url)
            it.onNext(peopleDTO)
        }
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
    }
}