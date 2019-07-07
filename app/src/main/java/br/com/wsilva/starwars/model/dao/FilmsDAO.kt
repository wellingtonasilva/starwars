package br.com.wsilva.starwars.model.dao

import androidx.room.*
import br.com.wsilva.starwars.core.BasicDAO
import br.com.wsilva.starwars.model.entity.FilmsEntity
import io.reactivex.Flowable

@Dao
interface FilmsDAO: BasicDAO<FilmsEntity> {
    @Query("SELECT * FROM films")
    override fun listAll(): Flowable<List<FilmsEntity>>

    @Query("SELECT * FROM films WHERE _id = :id")
    override fun get(id: Long): FilmsEntity

    @Delete
    override fun delete(entity: FilmsEntity): Int

    @Insert
    override fun insert(entity: FilmsEntity): Long

    @Update
    override fun update(entity: FilmsEntity): Int

    @Query("SELECT COUNT(1) FROM films WHERE _id = :id")
    fun exist(id: Long) : Int
}