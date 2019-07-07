package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.StarshipsDAO
import br.com.wsilva.starwars.model.dto.StarshipsDTO
import br.com.wsilva.starwars.model.entity.StarshipsEntity
import io.reactivex.Flowable

class StarshipsRepository(private val dao: StarshipsDAO): BasicRepository<StarshipsEntity>(dao) {
    override fun listAll(): Flowable<List<StarshipsEntity>> = dao.listAll()
    override fun get(id: Long): StarshipsEntity = dao.get(id)
    override fun delete(entity: StarshipsEntity): Int = dao.delete(entity)
    override fun insert(entity: StarshipsEntity): Long = dao.insert(entity)
    override fun update(entity: StarshipsEntity): Int = dao.update(entity)

    fun save(starshipsDTO: StarshipsDTO): Long {
        var entity = get(starshipsDTO.id)
        if (entity == null) {
            entity = StarshipsEntity(id = starshipsDTO.id, name = starshipsDTO.name, url = starshipsDTO.url,
                edited = starshipsDTO.edited,
                created = starshipsDTO.created, cargo_capacity = starshipsDTO.cargo_capacity,
                consumables = starshipsDTO.consumables,
                cost_in_credits = starshipsDTO.cost_in_credits, crew = starshipsDTO.crew,
                hyperdrive_rating = starshipsDTO.hyperdrive_rating,
                length = starshipsDTO.length, manufacturer = starshipsDTO.manufacturer,
                max_atmosphering_speed = starshipsDTO.max_atmosphering_speed,
                MGLT = starshipsDTO.MGLT, model = starshipsDTO.model, passengers = starshipsDTO.passengers,
                starship_class = starshipsDTO.starship_class)
            return insert(entity)
        }
        return entity.id
    }
}