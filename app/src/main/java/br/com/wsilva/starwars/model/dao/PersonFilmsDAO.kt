package br.com.wsilva.starwars.model.dao

import androidx.room.*
import br.com.wsilva.starwars.core.BasicDAO
import br.com.wsilva.starwars.model.entity.PeopleFilmsEntity
import io.reactivex.Flowable

@Dao
interface PersonFilmsDAO: BasicDAO<PeopleFilmsEntity> {
    @Query("SELECT * FROM person_films")
    override fun listAll(): Flowable<List<PeopleFilmsEntity>>

    @Query("SELECT * FROM person_films WHERE _id = :id")
    override fun get(id: Long): PeopleFilmsEntity

    @Query("SELECT * FROM person_films WHERE person_id = :peopleId AND films_id = :filmsId")
    fun getByPeopleIdAndFilmsId(peopleId: Long, filmsId: Long): PeopleFilmsEntity

    @Delete
    override fun delete(entity: PeopleFilmsEntity): Int

    @Insert
    override fun insert(entity: PeopleFilmsEntity): Long

    @Update
    override fun update(entity: PeopleFilmsEntity): Int

    @Query("SELECT COUNT(1) FROM person_films WHERE person_id = :peopleId AND films_id = :filmsId")
    fun exist(peopleId: Long, filmsId: Long): Int
}