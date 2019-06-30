package br.com.wsilva.starwars.features.about

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.starwars.R

class AboutFragment: Fragment() {

    companion object {
        val TAG: String = "AboutFragment"
        fun newInstance(): AboutFragment {
            return AboutFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_about_fragment, container, false)
        return view
    }
}