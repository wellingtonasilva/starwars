package br.com.wsilva.starwars.model.dao

import androidx.room.*
import br.com.wsilva.starwars.core.BasicDAO
import br.com.wsilva.starwars.model.entity.PeopleSpeciesEntity
import io.reactivex.Flowable

@Dao
interface PersonSpeciesDAO: BasicDAO<PeopleSpeciesEntity> {
    @Query("SELECT * FROM person_species")
    override fun listAll(): Flowable<List<PeopleSpeciesEntity>>

    @Query("SELECT * FROM person_species WHERE _id = :id")
    override fun get(id: Long): PeopleSpeciesEntity

    @Query("SELECT * FROM person_species WHERE person_id = :personId AND species_id = :speciesId")
    fun getByPeopleIdAndSpeciesId(personId: Long, speciesId: Long): PeopleSpeciesEntity

    @Delete
    override fun delete(entity: PeopleSpeciesEntity): Int

    @Insert
    override fun insert(entity: PeopleSpeciesEntity): Long

    @Update
    override fun update(entity: PeopleSpeciesEntity): Int

    @Query("SELECT COUNT(1) FROM person_species WHERE person_id = :personId AND species_id = :speciesId")
    fun exist(personId: Long, speciesId: Long) : Int
}