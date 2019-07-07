package br.com.wsilva.starwars.model.dao

import androidx.room.*
import br.com.wsilva.starwars.core.BasicDAO
import br.com.wsilva.starwars.model.entity.PeopleVehiclesEntity
import io.reactivex.Flowable

@Dao
interface PersonVehiclesDAO: BasicDAO<PeopleVehiclesEntity> {
    @Query("SELECT * FROM person_vehicles")
    override fun listAll(): Flowable<List<PeopleVehiclesEntity>>

    @Query("SELECT * FROM person_vehicles WHERE _id = :id")
    override fun get(id: Long): PeopleVehiclesEntity

    @Query("SELECT * FROM person_vehicles WHERE person_id = :personId AND vehicles_id = :vehiclesId")
    fun getByPeopleIdAndVehiclesId(personId: Long, vehiclesId: Long): PeopleVehiclesEntity

    @Delete
    override fun delete(entity: PeopleVehiclesEntity): Int

    @Insert
    override fun insert(entity: PeopleVehiclesEntity): Long

    @Update
    override fun update(entity: PeopleVehiclesEntity): Int

    @Query("SELECT COUNT(1) FROM person_vehicles WHERE person_id = :personId AND vehicles_id = :vehiclesId")
    fun exist(personId: Long, vehiclesId: Long) : Int
}