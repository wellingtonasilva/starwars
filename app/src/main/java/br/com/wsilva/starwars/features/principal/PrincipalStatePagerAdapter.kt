package br.com.wsilva.starwars.features.principal

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import br.com.wsilva.starwars.features.about.AboutFragment
import br.com.wsilva.starwars.features.home.HomeFragment

class PrincipalStatePagerAdapter(fm: androidx.fragment.app.FragmentManager, private val bundle: Bundle): androidx.fragment.app.FragmentStatePagerAdapter(fm)
{
    companion object {
        val PAGE_HOME           = 0
        val PAGE_ABOUT          = 1
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        var fragment = androidx.fragment.app.Fragment()

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