package com.rajand.nur.ui.surah

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajand.core.data.Resource
import com.rajand.core.presentation.ui.surah.SurahAdapter
import com.rajand.nur.R
import com.rajand.nur.databinding.ActivitySurahBinding
import com.rajand.nur.ui.detail.DetailSurahActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SurahActivity : AppCompatActivity() {

    private val surahViewModel: SurahViewModel by viewModel()

    private var _binding: ActivitySurahBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val surahAdapter = SurahAdapter()
        surahAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@SurahActivity, DetailSurahActivity::class.java)
            intent.putExtra(DetailSurahActivity.EXTRA_NUMBER, selectedData.numberSurah)
            intent.putExtra(DetailSurahActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        surahViewModel.surah.observe(this, { surah ->
            if (surah != null) {
                when (surah) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        surahAdapter.setData(surah.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = surah.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        with(binding.rvSurah) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = surahAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                val uri = Uri.parse("rajand://save")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}