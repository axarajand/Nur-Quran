package com.rajand.core.domain.usecase

import com.rajand.core.domain.model.Ayah
import com.rajand.core.domain.model.Surah
import com.rajand.core.domain.repository.IQuranRepository

class QuranInteractor(private val quranRepository: IQuranRepository) : QuranUseCase {

    override fun getAllSurah() = quranRepository.getAllSurah()

    override fun getAllAyah(numberSurah: Int) = quranRepository.getAllAyah(numberSurah)

    override fun getSaveSurah() = quranRepository.getSaveSurah()

    override fun setSaveSurah(surah: Surah, state: Boolean) = quranRepository.setSaveSurah(surah, state)

    override fun setSaveAyah(ayah: Ayah, state: Boolean) = quranRepository.setSaveAyah(ayah, state)
}