package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.PersonStarshipsDAO
import br.com.wsilva.starwars.model.entity.PeopleStarshipsEntity
import io.reactivex.Flowable

class PeopelStarshipsRepository(private val dao: PersonStarshipsDAO): BasicRepository<PeopleStarshipsEntity>(dao) {
    override fun listAll(): Flowable<List<PeopleStarshipsEntity>> = dao.listAll()
    override fun get(id: Long): PeopleStarshipsEntity = dao.get(id)
    fun getByPeopleIdAndStarshipId(peopleId: Long, starshipId: Long): PeopleStarshipsEntity = dao.getByPeopleIdAndStarshipId(peopleId, starshipId)
    override fun delete(entity: PeopleStarshipsEntity): Int = dao.delete(entity)
    override fun insert(entity: PeopleStarshipsEntity): Long = dao.insert(entity)
    override fun update(entity: PeopleStarshipsEntity): Int = dao.update(entity)
    fun exist(personId: Long, starshipId: Long) : Boolean = dao.exist(personId, starshipId) > 0

    fun save(peopleId: Long, starshipId: Long): Boolean {
        var entity = getByPeopleIdAndStarshipId(peopleId, starshipId)
        if (entity == null) {
            entity = PeopleStarshipsEntity(personId = peopleId, starshipsId = starshipId)
            return insert(entity) > 0
        }
        return true
    }
}