package br.com.wsilva.starwars.features.detail

import br.com.wsilva.starwars.core.BasicPresenter

interface DetailContract {
    interface View {

    }

    interface Presenter: BasicPresenter {
        fun loadPerson(id: Long)
    }
}