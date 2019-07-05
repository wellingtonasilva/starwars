package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.PlanetsDAO
import br.com.wsilva.starwars.model.entity.PlanetsEntity
import io.reactivex.Flowable

class PlanetsRepository(private val dao: PlanetsDAO): BasicRepository<PlanetsEntity>(dao) {
    override fun listAll(): Flowable<List<PlanetsEntity>> = dao.listAll()
    override fun get(id: Long): PlanetsEntity = dao.get(id)
    override fun delete(entity: PlanetsEntity): Int = dao.delete(entity)
    override fun insert(entity: PlanetsEntity): Long = dao.insert(entity)
    override fun update(entity: PlanetsEntity): Int = dao.update(entity)
}