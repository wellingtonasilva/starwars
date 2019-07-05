package br.com.wsilva.starwars.model.dao

import android.arch.persistence.room.*
import br.com.wsilva.starwars.core.BasicDAO
import br.com.wsilva.starwars.model.entity.PeopleEntity
import io.reactivex.Flowable

@Dao
interface PersonDAO: BasicDAO<PeopleEntity> {
    @Query("SELECT * FROM person")
    override fun listAll(): Flowable<List<PeopleEntity>>

    @Query("SELECT * FROM person WHERE _id = :id")
    override fun get(id: Long): PeopleEntity

    @Delete
    override fun delete(entity: PeopleEntity): Int

    @Insert
    override fun insert(entity: PeopleEntity): Long

    @Update
    override fun update(entity: PeopleEntity): Int
}