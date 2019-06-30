package br.com.wsilva.starwars.features.principal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import br.com.wsilva.starwars.features.about.AboutFragment
import br.com.wsilva.starwars.features.home.HomeFragment

class PrincipalStatePagerAdapter(fm: FragmentManager, private val bundle: Bundle): FragmentStatePagerAdapter(fm)
{
    companion object {
        val PAGE_HOME           = 0
        val PAGE_ABOUT          = 1
    }

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()

        when (position)
        {
            PAGE_HOME       -> fragment = HomeFragment.newInstance()
            PAGE_ABOUT  -> fragment = AboutFragment.newInstance()
        }
        fragment.arguments = bundle

        return fragment
    }

    override fun getCount(): Int {
        return 2
    }
}