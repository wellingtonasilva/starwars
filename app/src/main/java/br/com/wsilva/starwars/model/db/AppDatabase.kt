package br.com.wsilva.starwars.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.wsilva.starwars.model.dao.*
import br.com.wsilva.starwars.model.entity.*

@Database(version = 7,
    entities = [
        PeopleEntity::class,
        PlanetsEntity::class,
        SpeciesEntity::class,
        StarshipsEntity::class,
        VehiclesEntity::class,
        PeoplePlanetsEntity::class,
        PeopleSpeciesEntity::class,
        PeopleStarshipsEntity::class,
        PeopleVehiclesEntity::class,
        FilmsEntity::class,
        PeopleFilmsEntity::class
    ])
abstract class AppDatabase: RoomDatabase() {
    abstract fun getPersonDAO(): PersonDAO
    abstract fun getPlanetsDAO(): PlanetsDAO
    abstract fun getSpeciesDAO(): SpeciesDAO
    abstract fun getStarshipsDAO(): StarshipsDAO
    abstract fun getVehiclesDAO(): VehiclesDAO
    abstract fun getPersonPlanetsDAO(): PersonPlanetsDAO
    abstract fun getPersonSpeciesDAO(): PersonSpeciesDAO
    abstract fun getPersonStarshipsDAO(): PersonStarshipsDAO
    abstract fun getPersonVehiclesDAO(): PersonVehiclesDAO
    abstract fun getPersonFilmsDAO(): PersonFilmsDAO
    abstract fun getFilmsDAO(): FilmsDAO
}