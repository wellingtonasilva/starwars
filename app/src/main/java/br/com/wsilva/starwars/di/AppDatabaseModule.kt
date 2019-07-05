package br.com.wsilva.starwars.di

import android.app.Application
import android.arch.persistence.room.Room
import br.com.wsilva.starwars.model.dao.*
import br.com.wsilva.starwars.model.db.AppDatabase
import br.com.wsilva.starwars.model.repository.*
import dagger.Module
import dagger.Provides

@Module
class AppDatabaseModule {

    @Provides
    fun providesAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application.applicationContext,
            AppDatabase::class.java, "dabase.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesPersonDAO(appDatabase: AppDatabase): PersonDAO {
        return appDatabase.getPersonDAO()
    }

    @Provides
    fun providesPersonRepository(personDAO: PersonDAO): PeopleRepository {
        return PeopleRepository(personDAO)
    }

    @Provides
    fun providesPlanetsDAO(appDatabase: AppDatabase): PlanetsDAO {
        return appDatabase.getPlanetsDAO()
    }

    @Provides
    fun providesPlanetsRepository(planetsDAO: PlanetsDAO): PlanetsRepository {
        return PlanetsRepository(planetsDAO)
    }

    @Provides
    fun providesSpeciesDAO(appDatabase: AppDatabase): SpeciesDAO {
        return appDatabase.getSpeciesDAO()
    }

    @Provides
    fun providesSpeciesRepository(speciesDAO: SpeciesDAO): SpeciesRepository {
        return SpeciesRepository(speciesDAO)
    }

    @Provides
    fun providesStarshipsDAO(appDatabase: AppDatabase): StarshipsDAO {
        return appDatabase.getStarshipsDAO()
    }

    @Provides
    fun providesStarshipsRepository(starshipsDAO: StarshipsDAO): StarshipsRepository {
        return StarshipsRepository(starshipsDAO)
    }

    @Provides
    fun providesVehiclesDAO(appDatabase: AppDatabase): VehiclesDAO {
        return appDatabase.getVehiclesDAO()
    }

    @Provides
    fun providesVehiclesRepository(vehiclesDAO: VehiclesDAO): VehiclesRepository {
        return VehiclesRepository(vehiclesDAO)
    }

    @Provides
    fun providesPersonPlanetsDAO(appDatabase: AppDatabase): PersonPlanetsDAO {
        return appDatabase.getPersonPlanetsDAO()
    }

    @Provides
    fun providesPersonPlanetsRepository(personPlanetsDAO: PersonPlanetsDAO): PeoplePlanetsRepository {
        return PeoplePlanetsRepository(personPlanetsDAO)
    }

    @Provides
    fun providesPersonSpeciesDAO(appDatabase: AppDatabase): PersonSpeciesDAO {
        return appDatabase.getPersonSpeciesDAO()
    }

    @Provides
    fun providesPersonSpeciesRepository(personSpeciesDAO: PersonSpeciesDAO): PeopleSpeciesRepository {
        return PeopleSpeciesRepository(personSpeciesDAO)
    }

    @Provides
    fun provivdesPersonStarshipsDAO(appDatabase: AppDatabase): PersonStarshipsDAO {
        return appDatabase.getPersonStarshipsDAO()
    }

    @Provides
    fun provivdesPersonStarshipsRepository(personStarshipsDAO: PersonStarshipsDAO): PeopelStarshipsRepository {
        return PeopelStarshipsRepository(personStarshipsDAO)
    }

    @Provides
    fun providesPersonVehiclesDAO(appDatabase: AppDatabase): PersonVehiclesDAO {
        return appDatabase.getPersonVehiclesDAO()
    }

    @Provides
    fun providesPersonVehiclesRepository(personVehiclesDAO: PersonVehiclesDAO): PeopleVehiclesRepository {
        return PeopleVehiclesRepository(personVehiclesDAO)
    }

    @Provides
    fun providesFilmsDAO(appDatabase: AppDatabase): FilmsDAO {
        return appDatabase.getFilmsDAO()
    }

    @Provides
    fun providesFilmsRepository(filmsDAO: FilmsDAO): FilmsRepository {
        return FilmsRepository(filmsDAO)
    }

    @Provides
    fun providesPersonFilmsDAO(appDatabase: AppDatabase): PersonFilmsDAO {
        return appDatabase.getPersonFilmsDAO()
    }

    @Provides
    fun providesPersonFilmsRepository(personFilmsDAO: PersonFilmsDAO): PeopleFilmsRepository {
        return PeopleFilmsRepository(personFilmsDAO)
    }

    @Provides
    fun providesAppPeopleRepository(peopleRepository: PeopleRepository,
                                    filmsRepository: FilmsRepository, peopleFilmsRepository: PeopleFilmsRepository,
                                    speciesRepository: SpeciesRepository, peopleSpeciesRepository: PeopleSpeciesRepository,
                                    vehiclesRepository: VehiclesRepository, starshipsRepository: StarshipsRepository,
                                    peopleStarshipsRepository: PeopelStarshipsRepository, peopleVehiclesRepository: PeopleVehiclesRepository
        ): AppPeopleRepository {
        return AppPeopleRepository(peopleRepository, filmsRepository, peopleFilmsRepository, speciesRepository,
            peopleSpeciesRepository, vehiclesRepository, starshipsRepository, peopleStarshipsRepository,
            peopleVehiclesRepository)
    }
}
