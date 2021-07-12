package com.example.e_posyandu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_posyandu.databinding.ActivityDetailPenimbanganBinding
import com.example.e_posyandu.models.PenimbanganAnak

class DetailPenimbanganActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPenimbanganBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPenimbanganBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataToView()
        backButtonPressed()
    }


    private fun backButtonPressed(){
        binding.backbutton.setOnClickListener { finish() }
    }
    private fun getPenimbangan() : PenimbanganAnak = intent.getParcelableExtra("penimbangan")!!
    private fun showDataToView(){
        binding.tvTanggal.setText(getPenimbangan().tanggal)
        binding.tvBeratBadan.setText(getPenimbangan().beratbadan + " Kg")
        binding.tvJenisVitamin.setText(getPenimbangan().vitamin)
    }
}