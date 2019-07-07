package br.com.wsilva.starwars.features.detail

import android.os.Bundle
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.wsilva.starwars.AppApplication
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.constants.AppConstants
import br.com.wsilva.starwars.core.BasicFragment
import br.com.wsilva.starwars.di.AppModule
import br.com.wsilva.starwars.features.detail.di.DaggerDetailComponent
import br.com.wsilva.starwars.features.detail.di.DetailModule
import br.com.wsilva.starwars.model.entity.PeopleEntity
import br.com.wsilva.starwars.model.entity.VehiclesEntity
import kotlinx.android.synthetic.main.lay_detail_fragment.*
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
        presenter.peopleId = (savedInstanceState ?: arguments)?.getLong(AppConstants.KEY_PEOPLE_ID)!!
        //Nome
        presenter.peopleName = (savedInstanceState ?: arguments)?.getString(AppConstants.KEY_PEOPLE_NAME)!!
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_detail_fragment, container, false)
        activity!!.title = presenter.peopleName
        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { move() }

        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.loadPerson(presenter.peopleId)
    }

    override fun showGeneralInformation(people: PeopleEntity) {
        lblGenero.text = people.gender
        lblPlanetaNatal.text = people.homeworld
        lblCorPele.text = people.skinColor
    }

    override fun showVehicles(list: List<VehiclesEntity>) {
        val adapter = DetailVehiclesAdapter(activity!!, list, object: DetailVehiclesAdapter.Listener {
            override fun OnClickListener(vehicle: VehiclesEntity) {
                Toast.makeText(context, vehicle.name, Toast.LENGTH_SHORT).show()
            }
        })
        rcvListaVeiculos.setHasFixedSize(true)
        rcvListaVeiculos.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        rcvListaVeiculos.adapter = adapter
    }

    fun move() {
        val fab = view!!.findViewById<FloatingActionButton>(R.id.fab)
        val fling = FlingAnimation(fab, DynamicAnimation.SCROLL_Y).apply {
            setMinValue(0f)
            friction = 1.1f
            start()
        }
    }
}