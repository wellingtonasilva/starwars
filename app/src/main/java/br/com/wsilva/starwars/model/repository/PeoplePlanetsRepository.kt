package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.PersonPlanetsDAO
import br.com.wsilva.starwars.model.entity.PeoplePlanetsEntity
import io.reactivex.Flowable

class PeoplePlanetsRepository (private val dao: PersonPlanetsDAO): BasicRepository<PeoplePlanetsEntity>(dao) {
    override fun listAll(): Flowable<List<PeoplePlanetsEntity>> = dao.listAll()
    override fun get(id: Long): PeoplePlanetsEntity = dao.get(id)
    fun getByPeopleIdAndPlanetId(peopleId: Long, planetId: Long): PeoplePlanetsEntity = dao.getByPeopleIdAndPlanetId(peopleId, planetId)
    override fun delete(entity: PeoplePlanetsEntity): Int = dao.delete(entity)
    override fun insert(entity: PeoplePlanetsEntity): Long = dao.insert(entity)
    override fun update(entity: PeoplePlanetsEntity): Int = dao.update(entity)
    fun exist(peopleId: Long, planetId: Long) : Boolean = dao.exist(peopleId, planetId) > 0

    fun save(peopleId: Long, planetId: Long): Boolean {
        var entity = getByPeopleIdAndPlanetId(peopleId, planetId)
        if (entity == null) {
            return insert(PeoplePlanetsEntity(personId = peopleId, planetId = planetId)) > 0
        }
        return true
    }
}