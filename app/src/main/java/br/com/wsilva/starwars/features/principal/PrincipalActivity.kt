package br.com.wsilva.starwars.features.principal

import android.os.Bundle
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.core.BasicActivity

class PrincipalActivity: BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_principal_activity)
        //Configuração inicial
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?)
    {
        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentByTag(PrincipalFragment.TAG)
        if (fragment == null) {
            fragment = PrincipalFragment.newInstance()
        }
        if (savedInstanceState == null) {
            fragment.arguments = intent.extras
        } else {
            fragment.arguments = savedInstanceState
        }
        addFragmentToActivity(fragmentManager, fragment, R.id.frameLayout, PrincipalFragment.TAG)
    }
}