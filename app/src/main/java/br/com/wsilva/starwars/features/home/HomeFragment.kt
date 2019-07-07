package br.com.wsilva.starwars.features.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.starwars.AppApplication
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.constants.AppConstants
import br.com.wsilva.starwars.di.AppModule
import br.com.wsilva.starwars.features.detail.DetailActivity
import br.com.wsilva.starwars.features.home.di.DaggerHomeComponent
import br.com.wsilva.starwars.features.home.di.HomeModule
import br.com.wsilva.starwars.model.dto.PeopleDTO
import br.com.wsilva.starwars.util.AppUtils
import kotlinx.android.synthetic.main.lay_home_fragment.*
import javax.inject.Inject

class HomeFragment: androidx.fragment.app.Fragment(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    companion object {
        val TAG: String = "HomeFragment"
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        DaggerHomeComponent.builder()
            .appModule(AppModule(AppApplication.appComponent.application()))
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_home_fragment, container, false)
        activity!!.title = getText(R.string.app_star_war_people)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.loadAllPeople()
    }

    override fun showPeople(list: List<PeopleDTO>) {
        val adapter = HomeAdapter(activity!!, list, object: HomeAdapter.HomeAdapterListener {
            override fun OnClickListener(people: PeopleDTO) = showDetail(AppUtils.extractIdFromURL(people.url),
                people.name)
        })
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        recyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                context,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = adapter
    }

    override fun showDetail(id: Long, name: String) {
        val intent = Intent(context, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putLong(AppConstants.KEY_PEOPLE_ID, id)
        bundle.putString(AppConstants.KEY_PEOPLE_NAME, name)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}