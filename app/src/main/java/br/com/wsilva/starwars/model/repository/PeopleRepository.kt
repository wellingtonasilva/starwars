package br.com.wsilva.starwars.model.repository

import br.com.wsilva.starwars.constants.AppConstants
import br.com.wsilva.starwars.core.BasicRepository
import br.com.wsilva.starwars.model.dao.PersonDAO
import br.com.wsilva.starwars.model.dto.PeopleDTO
import br.com.wsilva.starwars.model.entity.PeopleEntity
import br.com.wsilva.starwars.util.AppUtils
import io.reactivex.Flowable

class PeopleRepository(private val dao: PersonDAO): BasicRepository<PeopleEntity>(dao) {
    override fun listAll(): Flowable<List<PeopleEntity>> = dao.listAll()
    override fun get(id: Long): PeopleEntity  = dao.get(id)
    override fun delete(entity: PeopleEntity): Int = dao.delete(entity)
    override fun insert(entity: PeopleEntity): Long = dao.insert(entity)
    override fun update(entity: PeopleEntity): Int = dao.update(entity)

    fun save(peopleId: Long, peopleDTO: PeopleDTO): PeopleDTO {
        var entity = get(peopleId)
        if (entity == null) {
            entity = PeopleEntity(id = peopleId, name = peopleDTO.name, birthYear = peopleDTO.birthYear,
                created = peopleDTO.created, edited = peopleDTO.edited, gender = peopleDTO.gender,
                hairColor = peopleDTO.hairColor, height = peopleDTO.height, homeworld = peopleDTO.homeWorldUrl,
                mass = peopleDTO.mass, skinColor = peopleDTO.skinColor, url = peopleDTO.url,
                eyeColor = peopleDTO.eyeColor)
            insert(entity)
        }
        return peopleDTO
    }

    fun save(list: List<PeopleDTO>): List<PeopleDTO> {
        list.forEach { item -> save(AppUtils.extractIdFromURL(item.url), item)}
        return list
    }
}