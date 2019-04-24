package com.example.amaze.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.amaze.fragments.DispoFragment
import com.example.amaze.fragments.InfoFragment
import com.example.amaze.fragments.OptionsFragment
import com.example.amaze.fragments.PaiementsFragment

class CustomPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    val fragments = arrayOf(InfoFragment(), DispoFragment(), PaiementsFragment(), OptionsFragment())
    val titles = arrayOf("Info", "Dispo", "Paiements", "Options")

    // Retourne la vue affichée dans le ViewPager
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    // Retourne le nombre de vues à afficher
    override fun getCount(): Int {
        return fragments.size
    }

    // Retourner le titre du bouton à afficher (TabLayout)
    override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
    }

}