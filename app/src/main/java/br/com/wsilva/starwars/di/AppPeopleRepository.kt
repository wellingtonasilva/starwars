package br.com.wsilva.starwars.di

import br.com.wsilva.starwars.model.repository.*
import javax.inject.Inject

class AppPeopleRepository {

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
    constructor(peopleRepository: PeopleRepository,
                filmsRepository: FilmsRepository, peopleFilmsRepository: PeopleFilmsRepository,
                speciesRepository: SpeciesRepository, peopleSpeciesRepository: PeopleSpeciesRepository,
                vehiclesRepository: VehiclesRepository, starshipsRepository: StarshipsRepository,
                peopleStarshipsRepository: PeopelStarshipsRepository, peopleVehiclesRepository: PeopleVehiclesRepository) {
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
}