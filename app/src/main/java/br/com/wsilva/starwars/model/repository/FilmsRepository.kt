package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.FilmsDAO
import br.com.wsilva.starwars.model.dto.FilmsDTO
import br.com.wsilva.starwars.model.entity.FilmsEntity
import io.reactivex.Flowable

class FilmsRepository (private val dao: FilmsDAO): BasicRepository<FilmsEntity>(dao) {
    override fun listAll(): Flowable<List<FilmsEntity>> = dao.listAll()
    override fun get(id: Long): FilmsEntity = dao.get(id)
    override fun delete(entity: FilmsEntity): Int = dao.delete(entity)
    override fun insert(entity: FilmsEntity): Long = dao.insert(entity)
    override fun update(entity: FilmsEntity): Int = dao.update(entity)

    fun save(filmsDTO: FilmsDTO): Long {
        var entity = get(filmsDTO.id)
        if (entity == null) {
            entity = FilmsEntity(id = filmsDTO.id, url = filmsDTO.url, edited = filmsDTO.edited,
                created = filmsDTO.created, director = filmsDTO.director, episodeId = filmsDTO.episodeId,
                opening_crawl = filmsDTO.opening_crawl, producer = filmsDTO.producer,
                release_date = filmsDTO.release_date, title = filmsDTO.title)
            return insert(entity)
        }
        return entity.id
    }
}