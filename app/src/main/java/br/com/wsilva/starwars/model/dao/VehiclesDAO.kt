package br.com.wsilva.starwars.model.dao

import androidx.room.*
import br.com.wsilva.starwars.core.BasicDAO
import br.com.wsilva.starwars.model.entity.VehiclesEntity
import io.reactivex.Flowable

@Dao
interface VehiclesDAO: BasicDAO<VehiclesEntity> {
    @Query("SELECT * FROM vehicles")
    override fun listAll(): Flowable<List<VehiclesEntity>>

    @Query("SELECT * FROM vehicles WHERE _id = :id")
    override fun get(id: Long): VehiclesEntity

    @Query("SELECT * FROM vehicles v WHERE v._id IN (SELECT pv.vehicles_id FROM person_vehicles pv WHERE pv.person_id = :personId )")
    fun listAllByPeopleId(personId: Long): Flowable<List<VehiclesEntity>>

    @Delete
    override fun delete(entity: VehiclesEntity): Int

    @Insert
    override fun insert(entity: VehiclesEntity): Long

    @Update
    override fun update(entity: VehiclesEntity): Int
}