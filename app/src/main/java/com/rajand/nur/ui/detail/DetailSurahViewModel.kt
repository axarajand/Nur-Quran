package com.rajand.nur.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rajand.core.domain.model.Surah
import com.rajand.core.domain.usecase.QuranUseCase

class DetailSurahViewModel(private val quranUseCase: QuranUseCase) : ViewModel() {

    private val numberSurah = MutableLiveData<Int>()

    fun setSelectedSurah(numberSurah: Int) {
        this.numberSurah.value = numberSurah
    }

    val ayah = Transformations.switchMap(numberSurah) { mNumberSurah ->
        quranUseCase.getAllAyah(mNumberSurah).asLiveData()
    }

    fun setSaveSurah(surah: Surah, newState: Boolean) =
        quranUseCase.setSaveSurah(surah, newState)
}