package br.com.wsilva.starwars.model.dao

import androidx.room.*
import br.com.wsilva.starwars.core.BasicDAO
import br.com.wsilva.starwars.model.entity.PeoplePlanetsEntity
import io.reactivex.Flowable

@Dao
interface PersonPlanetsDAO: BasicDAO<PeoplePlanetsEntity> {
    @Query("SELECT * FROM person_planets")
    override fun listAll(): Flowable<List<PeoplePlanetsEntity>>

    @Query("SELECT * FROM person_planets WHERE _id = :id")
    override fun get(id: Long): PeoplePlanetsEntity

    @Delete
    override fun delete(entity: PeoplePlanetsEntity): Int

    @Insert
    override fun insert(entity: PeoplePlanetsEntity): Long

    @Update
    override fun update(entity: PeoplePlanetsEntity): Int
}