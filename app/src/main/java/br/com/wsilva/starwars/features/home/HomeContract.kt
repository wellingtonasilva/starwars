package br.com.wsilva.starwars.features.home

import br.com.wsilva.starwars.core.BasicPresenter
import br.com.wsilva.starwars.model.dto.GenericDTO
import br.com.wsilva.starwars.model.dto.PeopleDTO

interface HomeContract {
    interface View {
        fun showPeople(list: List<PeopleDTO>)
        fun showDetail(id: Long, name: String)
    }

    interface Presenter: BasicPresenter {
        fun loadAllPeople()
        fun afterLoadAllPeople()
    }
}