package br.com.wsilva.starwars.model.dao

import androidx.room.*
import br.com.wsilva.starwars.core.BasicDAO
import br.com.wsilva.starwars.model.entity.SpeciesEntity
import io.reactivex.Flowable

@Dao
interface SpeciesDAO: BasicDAO<SpeciesEntity> {
    @Query("SELECT * FROM species")
    override fun listAll(): Flowable<List<SpeciesEntity>>

    @Query("SELECT * FROM species WHERE _id = :id")
    override fun get(id: Long): SpeciesEntity

    @Delete
    override fun delete(entity: SpeciesEntity): Int

    @Insert
    override fun insert(entity: SpeciesEntity): Long

    @Update
    override fun update(entity: SpeciesEntity): Int

    @Query("SELECT COUNT(1) FROM species WHERE _id = :id")
    fun exist(id: Long) : Int
}