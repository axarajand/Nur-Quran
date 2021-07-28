package com.rajand.save.ui.save

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rajand.core.domain.usecase.QuranUseCase

class SaveViewModel(quranUseCase: QuranUseCase) : ViewModel() {

    val save = quranUseCase.getSaveSurah().asLiveData()
}