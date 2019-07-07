package br.com.wsilva.starwars.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity

open class BasicActivity: AppCompatActivity()
{
    fun addFragmentToActivity(fragmentManager: androidx.fragment.app.FragmentManager,
                              fragment: androidx.fragment.app.Fragment,
                              frameId: Int,
                              tag: String)
    {

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(frameId, fragment, tag)
        transaction.commit()
    }
}