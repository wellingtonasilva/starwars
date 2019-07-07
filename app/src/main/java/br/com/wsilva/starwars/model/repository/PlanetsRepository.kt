package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.PlanetsDAO
import br.com.wsilva.starwars.model.dto.PlanetsDTO
import br.com.wsilva.starwars.model.entity.PlanetsEntity
import io.reactivex.Flowable

class PlanetsRepository(private val dao: PlanetsDAO): BasicRepository<PlanetsEntity>(dao) {
    override fun listAll(): Flowable<List<PlanetsEntity>> = dao.listAll()
    override fun get(id: Long): PlanetsEntity = dao.get(id)
    override fun delete(entity: PlanetsEntity): Int = dao.delete(entity)
    override fun insert(entity: PlanetsEntity): Long = dao.insert(entity)
    override fun update(entity: PlanetsEntity): Int = dao.update(entity)
    fun exist(id: Long): Boolean = dao.exist(id) > 0

    fun save(planetsDTO: PlanetsDTO) {
        val entity = get(planetsDTO.id)
        if (entity == null) {
            insert(PlanetsEntity(id = planetsDTO.id, name = planetsDTO.name, url = planetsDTO.url,
                edited = planetsDTO.edited, created = planetsDTO.created, climate = planetsDTO.climate,
                diameter = planetsDTO.diameter, gravity = planetsDTO.gravity, orbital_period = planetsDTO.orbital_period,
                population = planetsDTO.population, rotation_period = planetsDTO.rotation_period,
                surface_water = planetsDTO.surface_water, terrain = planetsDTO.terrain))
        }
    }
}