package br.com.wsilva.starwars.features.detail

import br.com.wsilva.starwars.core.BasicPresenter
import br.com.wsilva.starwars.model.entity.PeopleEntity
import br.com.wsilva.starwars.model.entity.VehiclesEntity

interface DetailContract {
    interface View {
        fun showGeneralInformation(people: PeopleEntity)
        fun showVehicles(list: List<VehiclesEntity>)
    }

    interface Presenter: BasicPresenter {
        fun loadPerson(id: Long)
    }
}