package com.rajand.save.ui.save

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajand.core.presentation.ui.surah.SurahAdapter
import com.rajand.nur.ui.detail.DetailSurahActivity
import com.rajand.save.databinding.ActivitySaveBinding
import com.rajand.save.di.saveModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class

SaveActivity : AppCompatActivity() {

    private val saveViewModel: SaveViewModel by viewModel()

    private var _binding: ActivitySaveBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadKoinModules(saveModule)

        val surahAdapter = SurahAdapter()
        surahAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@SaveActivity, DetailSurahActivity::class.java)
            intent.putExtra(DetailSurahActivity.EXTRA_NUMBER, selectedData.numberSurah)
            intent.putExtra(DetailSurahActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        saveViewModel.save.observe(this, { save ->
                surahAdapter.setData(save)
                binding.viewEmpty.root.visibility = if (save.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvSave) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = surahAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}