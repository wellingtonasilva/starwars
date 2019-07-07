package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.PersonVehiclesDAO
import br.com.wsilva.starwars.model.entity.PeopleVehiclesEntity
import io.reactivex.Flowable

class PeopleVehiclesRepository(private val dao: PersonVehiclesDAO): BasicRepository<PeopleVehiclesEntity>(dao) {
    override fun listAll(): Flowable<List<PeopleVehiclesEntity>> = dao.listAll()
    override fun get(id: Long): PeopleVehiclesEntity = dao.get(id)
    fun getByPeopleIdAndVehiclesId(personId: Long, vehiclesId: Long): PeopleVehiclesEntity = dao.getByPeopleIdAndVehiclesId(personId, vehiclesId)
    override fun delete(entity: PeopleVehiclesEntity): Int = dao.delete(entity)
    override fun insert(entity: PeopleVehiclesEntity): Long = dao.insert(entity)
    override fun update(entity: PeopleVehiclesEntity): Int = dao.update(entity)
    fun exist(personId: Long, vehiclesId: Long) : Boolean = dao.exist(personId, vehiclesId) > 0

    fun save(peopleId: Long, vehiclesId: Long): Boolean? {
        var entity = getByPeopleIdAndVehiclesId(peopleId, vehiclesId)
        return if (entity == null) {
            entity = PeopleVehiclesEntity(personId = peopleId, vehiclesId = vehiclesId)
            insert(entity) > 0
        } else {
            true
        }
    }
}