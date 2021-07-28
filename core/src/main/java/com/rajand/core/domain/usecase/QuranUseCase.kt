package com.rajand.core.domain.usecase

import com.rajand.core.data.Resource
import com.rajand.core.domain.model.Ayah
import com.rajand.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface QuranUseCase {

    fun getAllSurah(): Flow<Resource<List<Surah>>>

    fun getAllAyah(numberSurah: Int): Flow<Resource<List<Ayah>>>

    fun getSaveSurah(): Flow<List<Surah>>

    fun setSaveSurah(surah: Surah, state: Boolean)

    fun setSaveAyah(ayah: Ayah, state: Boolean)
}