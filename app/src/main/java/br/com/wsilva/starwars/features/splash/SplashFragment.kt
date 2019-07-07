package br.com.wsilva.starwars.features.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.starwars.AppApplication
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.core.BasicFragment
import br.com.wsilva.starwars.di.AppDatabaseModule
import br.com.wsilva.starwars.di.AppModule
import br.com.wsilva.starwars.features.principal.PrincipalActivity
import br.com.wsilva.starwars.features.splash.di.DaggerSplashComponent
import br.com.wsilva.starwars.features.splash.di.SplashModule
import javax.inject.Inject

class SplashFragment: BasicFragment(), SplashContract.View  {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    companion object {
        val TAG: String = "SplashFragment"
        fun newInstance(): SplashFragment {
            return SplashFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        DaggerSplashComponent.builder()
            .splashModule(SplashModule(this))
            .appModule(AppModule(AppApplication.appComponent.application()))
            .appDatabaseModule(AppDatabaseModule())
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_splash_fragment, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.loadAllPeople()
        presenter.startAnimation(context!!, view!!.findViewById(R.id.imgShip1),
            view!!.findViewById(R.id.imgShip2), view!!.findViewById(R.id.imgLogo))
    }

    override fun showPrincipal() {
        val intent = Intent(context, PrincipalActivity::class.java)
        startActivity(intent)
        activity!!.finish()
    }
}