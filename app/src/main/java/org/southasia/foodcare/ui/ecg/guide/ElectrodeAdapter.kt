package org.southasia.foodcare.ui.ecg.guide

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

// 1
class ElectrodeAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    // 2
    override fun getItem(position: Int): Fragment {
        return ElectrodeFragment()
    }

    // 3
    override fun getCount(): Int {
        return 3
    }
}