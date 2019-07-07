package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.SpeciesDAO
import br.com.wsilva.starwars.model.dto.SpeciesDTO
import br.com.wsilva.starwars.model.entity.SpeciesEntity
import io.reactivex.Flowable

class SpeciesRepository(private val dao: SpeciesDAO): BasicRepository<SpeciesEntity>(dao) {
    override fun listAll(): Flowable<List<SpeciesEntity>> = dao.listAll()
    override fun get(id: Long): SpeciesEntity = dao.get(id)
    override fun delete(entity: SpeciesEntity): Int = dao.delete(entity)
    override fun insert(entity: SpeciesEntity): Long = dao.insert(entity)
    override fun update(entity: SpeciesEntity): Int = dao.update(entity)

    fun save(speciesDTO: SpeciesDTO): Long {
        var entity = get(speciesDTO.id)
        if (entity == null) {
            entity = SpeciesEntity(id = speciesDTO.id, created = speciesDTO.created, edited = speciesDTO.edited,
                url = speciesDTO.url, homeworld = speciesDTO.homeworld, name = speciesDTO.name,
                language = speciesDTO.language, average_height = speciesDTO.average_height,
                average_lifespan = speciesDTO.average_lifespan, classification = speciesDTO.classification,
                designation = speciesDTO.designation, eye_colors = speciesDTO.eye_colors,
                hair_colors = speciesDTO.hair_colors, skin_colors = speciesDTO.skin_colors)
            return insert(entity)
        }
        return entity.id
    }
}