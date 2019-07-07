package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.PersonFilmsDAO
import br.com.wsilva.starwars.model.entity.PeopleFilmsEntity
import io.reactivex.Flowable

class PeopleFilmsRepository (private val dao: PersonFilmsDAO): BasicRepository<PeopleFilmsEntity>(dao) {
    override fun listAll(): Flowable<List<PeopleFilmsEntity>> = dao.listAll()
    override fun get(id: Long): PeopleFilmsEntity = dao.get(id)
    fun getByPeopleIdAndFilmsId(peopleId: Long, filmsId: Long): PeopleFilmsEntity = dao.getByPeopleIdAndFilmsId(peopleId, filmsId)
    override fun delete(entity: PeopleFilmsEntity): Int = dao.delete(entity)
    override fun insert(entity: PeopleFilmsEntity): Long = dao.insert(entity)
    override fun update(entity: PeopleFilmsEntity): Int = dao.update(entity)
    fun exist(peopleId: Long, filmsId: Long): Boolean = dao.exist(peopleId, filmsId) > 0

    fun save(peopleId: Long, filmsId: Long): Boolean {
        var entity = getByPeopleIdAndFilmsId(peopleId, filmsId)
        if (entity == null) {
            return insert(PeopleFilmsEntity(personId = peopleId, filmsId = filmsId)) > 0
        }
        return true
    }
}