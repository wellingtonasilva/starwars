package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.PersonSpeciesDAO
import br.com.wsilva.starwars.model.entity.PeopleSpeciesEntity
import io.reactivex.Flowable

class PeopleSpeciesRepository(private val dao: PersonSpeciesDAO): BasicRepository<PeopleSpeciesEntity>(dao) {
    override fun listAll(): Flowable<List<PeopleSpeciesEntity>> = dao.listAll()
    override fun get(id: Long): PeopleSpeciesEntity = dao.get(id)
    fun getByPeopleIdAndSpeciesId(personId: Long, speciesId: Long): PeopleSpeciesEntity = dao.getByPeopleIdAndSpeciesId(personId, speciesId)
    override fun delete(entity: PeopleSpeciesEntity): Int = dao.delete(entity)
    override fun insert(entity: PeopleSpeciesEntity): Long = dao.insert(entity)
    override fun update(entity: PeopleSpeciesEntity): Int = dao.update(entity)

    fun save(peopleId: Long, speciesId: Long): Boolean {
        var entity = getByPeopleIdAndSpeciesId(peopleId, speciesId)
        if (entity == null) {
            entity = PeopleSpeciesEntity(peopleId, speciesId)
            return insert(entity) > 0
        }
        return true
    }
}