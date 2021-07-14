package com.example.e_posyandu.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.e_posyandu.fragment.PemeriksaanBumilFragment
import com.example.e_posyandu.fragment.PenimbanganBumilFragment
import java.util.*

@Suppress("DEPRECATION")
class ViewPagerBumilAdapter(supportFragmentManager: FragmentManager): FragmentPagerAdapter
    (supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private var idBumil : String? = null
    private val  mFragmentList : MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 ->{
                PenimbanganBumilFragment()
            }
            else ->{
                PemeriksaanBumilFragment()
            }

        }
    }

    override fun getCount(): Int = mFragmentList.size

    fun addFragment(fragment: Fragment?, idBumil : String?){
        mFragmentList.add(fragment!!)
        this.idBumil = idBumil
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Jadwal"
            else -> "Pemeriksaan"

        }
    }
}