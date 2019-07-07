package br.com.wsilva.starwars.features.home

import br.com.wsilva.starwars.core.BasicPresenter
import br.com.wsilva.starwars.model.entity.PeopleEntity

interface HomeContract {
    interface View {
        fun showPeople(list: List<PeopleEntity>)
        fun showDetail(id: Long, name: String)
    }

    interface Presenter: BasicPresenter {
        fun loadAllPeople()
    }
}