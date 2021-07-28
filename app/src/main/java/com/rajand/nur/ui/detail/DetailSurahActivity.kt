package com.rajand.nur.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajand.core.data.Resource
import com.rajand.core.databinding.ContentDetailSurahBinding
import com.rajand.core.domain.model.Surah
import com.rajand.core.presentation.ui.ayah.AyahAdapter
import com.rajand.nur.R
import com.rajand.nur.databinding.ActivityDetailSurahBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailSurahActivity : AppCompatActivity() {

    private val detailSurahViewModel: DetailSurahViewModel by viewModel()

    private var _binding: ActivityDetailSurahBinding? = null
    private val binding get() = _binding!!

    private var _detailContentBinding: ContentDetailSurahBinding? = null
    private val detailContentBinding get() = _detailContentBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        _detailContentBinding = binding.detailContent
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val ayahAdapter = AyahAdapter()

        val detailSurah = intent.getParcelableExtra<Surah>(EXTRA_DATA)
        val extras = intent.extras
        if (extras != null) {
            val numberSurah = extras.getInt(EXTRA_NUMBER)
            if (numberSurah != 0) {
                detailSurahViewModel.setSelectedSurah(numberSurah)
                detailSurahViewModel.ayah.observe(this, { ayah ->
                    if (ayah != null) {
                        when (ayah) {
                            is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                            is Resource.Success -> {
                                binding.progressBar.visibility = View.GONE
                                binding.nestedScrollView.visibility = View.VISIBLE
                                ayahAdapter.setData(ayah.data)
                                populateSurah(detailSurah)
                            }
                            is Resource.Error -> {
                                binding.progressBar.visibility = View.GONE
                                binding.viewError.root.visibility = View.VISIBLE
                                binding.viewError.tvError.text = ayah.message ?: getString(R.string.something_wrong)
                            }
                        }
                    }
                })

                with(detailContentBinding.rvAyah) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = ayahAdapter
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        _detailContentBinding = null
    }

    private fun populateSurah(detailSurah: Surah?) {
        detailSurah?.let {
            detailContentBinding.tvNameSurah.text = detailSurah.name
            detailContentBinding.tvNameTranslation.text = detailSurah.translationName
            detailContentBinding.tvRevelationSurah.text = detailSurah.revelation
            detailContentBinding.tvNumberOfVerses.text = detailSurah.numberOfVerses.toString()

            var statusSave = detailSurah.save
            setStatusSave(statusSave)
            detailContentBinding.imgSaveSurah.setOnClickListener {
                statusSave = !statusSave
                detailSurahViewModel.setSaveSurah(detailSurah, statusSave)
                setStatusSave(statusSave)
            }
        }
    }

    private fun setStatusSave(statusSave: Boolean) {
        if (statusSave) {
            detailContentBinding.imgSaveSurah.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_white_24))
        } else {
            detailContentBinding.imgSaveSurah.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_border_white_24))
        }
    }

    companion object {
        const val EXTRA_NUMBER = "extra_number"
        const val EXTRA_DATA = "extra_data"
    }
}