package com.example.e_posyandu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_posyandu.adapters.ViewPagerAnakAdapter
import com.example.e_posyandu.databinding.ActivityDetailCatatanAnakBinding
import com.example.e_posyandu.fragment.GrafikPenimbanganFragment
import com.example.e_posyandu.fragment.JadwalAnakFragment
import com.example.e_posyandu.fragment.PenimbanganAnakFragment
import com.example.e_posyandu.fragment.StatusImunisasiFragment

class DetailCatatanAnakActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailCatatanAnakBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCatatanAnakBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar.title = "Detail Catatan Anak"
        setupViewPager()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupViewPager(){
        val userId = intent.getStringExtra("userIdAnak")
        val anakViewPager = ViewPagerAnakAdapter(supportFragmentManager)
        val fragmentPenimbangan = PenimbanganAnakFragment.getUserIdAnak(userId!!)
        val fragmentJadwal = JadwalAnakFragment.getUserIdAnak(userId)
        val fragmentImunisasi = StatusImunisasiFragment.getUserIdAnak(userId)
        val fragmentGrafik = GrafikPenimbanganFragment.newInstance(userId);
        anakViewPager.addFragment(fragmentJadwal, userId)
        anakViewPager.addFragment(fragmentPenimbangan, userId)
        anakViewPager.addFragment(fragmentImunisasi, userId)
        anakViewPager.addFragment(fragmentGrafik, userId)
        binding.viewPager.adapter = anakViewPager
        binding.tabs.setupWithViewPager(binding.viewPager)
        println("UserId Detail = $userId")
    }



}
