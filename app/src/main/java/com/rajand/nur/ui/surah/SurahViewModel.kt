package com.rajand.nur.ui.surah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rajand.core.domain.usecase.QuranUseCase

class SurahViewModel(quranUseCase: QuranUseCase) : ViewModel() {

    val surah = quranUseCase.getAllSurah().asLiveData()
}