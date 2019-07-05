package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.PersonPlanetsDAO
import br.com.wsilva.starwars.model.entity.PeoplePlanetsEntity
import io.reactivex.Flowable

class PeoplePlanetsRepository (private val dao: PersonPlanetsDAO): BasicRepository<PeoplePlanetsEntity>(dao) {
    override fun listAll(): Flowable<List<PeoplePlanetsEntity>> = dao.listAll()
    override fun get(id: Long): PeoplePlanetsEntity = dao.get(id)
    override fun delete(entity: PeoplePlanetsEntity): Int = dao.delete(entity)
    override fun insert(entity: PeoplePlanetsEntity): Long = dao.insert(entity)
    override fun update(entity: PeoplePlanetsEntity): Int = dao.update(entity)
}