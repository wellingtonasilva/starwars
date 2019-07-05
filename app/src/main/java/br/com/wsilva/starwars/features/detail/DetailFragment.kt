package br.com.wsilva.starwars.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.starwars.AppApplication
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.core.BasicFragment
import br.com.wsilva.starwars.di.AppModule
import br.com.wsilva.starwars.features.detail.di.DaggerDetailComponent
import br.com.wsilva.starwars.features.detail.di.DetailModule
import javax.inject.Inject

class DetailFragment: BasicFragment(), DetailContract.View {

    @Inject
    lateinit var presenter: DetailPresenter

    companion object {
        val TAG: String = "DetailFragment"
        fun newInstance(): DetailFragment {
            return DetailFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        DaggerDetailComponent.builder()
            .appModule(AppModule(AppApplication.appComponent.application()))
            .detailModule(DetailModule(this))
            .build()
            .inject(this)
        //Código
        presenter.personId = (savedInstanceState ?: arguments)?.getLong("KEY_PERSON_ID")!!
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_detail_fragment, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.loadPerson(presenter.personId)
    }
}