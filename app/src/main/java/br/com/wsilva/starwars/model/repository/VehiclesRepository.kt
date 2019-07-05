package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.VehiclesDAO
import br.com.wsilva.starwars.model.dto.VehiclesDTO
import br.com.wsilva.starwars.model.entity.VehiclesEntity
import io.reactivex.Flowable

class VehiclesRepository(private val dao: VehiclesDAO): BasicRepository<VehiclesEntity>(dao) {
    override fun listAll(): Flowable<List<VehiclesEntity>> = dao.listAll()
    override fun get(id: Long): VehiclesEntity = dao.get(id)
    override fun delete(entity: VehiclesEntity): Int = dao.delete(entity)
    override fun insert(entity: VehiclesEntity): Long = dao.insert(entity)
    override fun update(entity: VehiclesEntity): Int = dao.update(entity)

    fun save(id: Long, vehicles: VehiclesDTO): Long {
        var entity = get(id)
        return if (entity == null) {
            entity = VehiclesEntity(id = id, passengers = vehicles.passengers, model = vehicles.model,
                max_atmosphering_speed = vehicles.max_atmosphering_speed, manufacturer = vehicles.manufacturer,
                length = vehicles.length, crew = vehicles.crew, cost_in_credits = vehicles.cost_in_credits,
                consumables = vehicles.consumables, cargo_capacity = vehicles.cargo_capacity, created = vehicles.created,
                edited = vehicles.edited, url = vehicles.url, name = vehicles.name, vehicle_class = vehicles.vehicle_class)
            insert(entity)
        } else {
            entity.id
        }

    }
}