package br.com.wsilva.starwars.features.detail

import android.os.Bundle
import br.com.wsilva.starwars.R
import br.com.wsilva.starwars.core.BasicActivity

class DetailActivity: BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_detail_activity)
        //Configuração inicial
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?)
    {
        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentByTag(DetailFragment.TAG)
        if (fragment == null) {
            fragment = DetailFragment.newInstance()
        }
        if (savedInstanceState == null) {
            fragment.arguments = intent.extras
        } else {
            fragment.arguments = savedInstanceState
        }
        addFragmentToActivity(fragmentManager, fragment, R.id.frameLayout, DetailFragment.TAG)
    }
}