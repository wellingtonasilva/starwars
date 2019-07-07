package br.com.wsilva.starwars.model.dao

import androidx.room.*
import br.com.wsilva.starwars.core.BasicDAO
import br.com.wsilva.starwars.model.entity.PeopleStarshipsEntity
import io.reactivex.Flowable

@Dao
interface PersonStarshipsDAO: BasicDAO<PeopleStarshipsEntity> {
    @Query("SELECT * FROM person_starships")
    override fun listAll(): Flowable<List<PeopleStarshipsEntity>>

    @Query("SELECT * FROM person_starships WHERE _id = :id")
    override fun get(id: Long): PeopleStarshipsEntity

    @Query("SELECT * FROM person_starships WHERE person_id = :peopleId AND starships_id = :starshipId")
    fun getByPeopleIdAndStarshipId(peopleId: Long, starshipId: Long): PeopleStarshipsEntity

    @Delete
    override fun delete(entity: PeopleStarshipsEntity): Int

    @Insert
    override fun insert(entity: PeopleStarshipsEntity): Long

    @Update
    override fun update(entity: PeopleStarshipsEntity): Int
}