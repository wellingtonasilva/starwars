package br.com.wsilva.starwars.features.principal

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.starwars.R
import kotlinx.android.synthetic.main.lay_principal_fragment.*

class PrincipalFragment: androidx.fragment.app.Fragment() {

    lateinit var mListPagerAdapter: PrincipalStatePagerAdapter
    lateinit var mViewPager: androidx.viewpager.widget.ViewPager
    lateinit var mBottomNavigationView: BottomNavigationView

    companion object {
        val TAG: String = "PrincipalFragment"
        fun newInstance(): PrincipalFragment {
            return PrincipalFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_principal_fragment, container, false)

        //Lista de Páginas a serem exibidas
        mListPagerAdapter = PrincipalStatePagerAdapter(fragmentManager!!, arguments ?: Bundle())

        //ViewPage
        mViewPager = view?.findViewById<androidx.viewpager.widget.ViewPager>(R.id.viewPager)!!
        mViewPager.adapter = mListPagerAdapter
        mViewPager.addOnPageChangeListener(object: androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    PrincipalStatePagerAdapter.PAGE_HOME -> navigation.selectedItemId = R.id.navigation_home
                    PrincipalStatePagerAdapter.PAGE_ABOUT -> navigation.selectedItemId = R.id.navigation_about
                }
            }
        })
        mBottomNavigationView = view?.findViewById(R.id.navigation)
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        return view
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mViewPager.currentItem = PrincipalStatePagerAdapter.PAGE_HOME
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_about -> {
                mViewPager.currentItem = PrincipalStatePagerAdapter.PAGE_ABOUT
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}