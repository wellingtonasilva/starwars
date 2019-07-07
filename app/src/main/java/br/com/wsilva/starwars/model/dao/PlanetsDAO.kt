package br.com.wsilva.starwars.model.dao

import androidx.room.*
import br.com.wsilva.starwars.core.BasicDAO
import br.com.wsilva.starwars.model.entity.PlanetsEntity
import io.reactivex.Flowable

@Dao
interface PlanetsDAO: BasicDAO<PlanetsEntity> {
    @Query("SELECT * FROM planets")
    override fun listAll(): Flowable<List<PlanetsEntity>>

    @Query("SELECT * FROM planets WHERE _id = :id")
    override fun get(id: Long): PlanetsEntity

    @Delete
    override fun delete(entity: PlanetsEntity): Int

    @Insert
    override fun insert(entity: PlanetsEntity): Long

    @Update
    override fun update(entity: PlanetsEntity): Int
}