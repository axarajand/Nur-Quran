package com.rajand.core.data.source.local

import com.rajand.core.data.source.local.entity.AyahEntity
import com.rajand.core.data.source.local.entity.SurahEntity
import com.rajand.core.data.source.local.room.QuranDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val quranDao: QuranDao) {

    fun getAllSurah(): Flow<List<SurahEntity>> = quranDao.getAllSurah()

    fun getAllAyah(numberSurah: Int): Flow<List<AyahEntity>> = quranDao.getAllAyah(numberSurah)

    fun getSaveSurah(): Flow<List<SurahEntity>> = quranDao.getSaveSurah()

    suspend fun insertSurah(surahList: List<SurahEntity>) = quranDao.insertSurah(surahList)

    suspend fun insertAyah(ayahList: List<AyahEntity>) = quranDao.insertAyah(ayahList)

    fun setSaveSurah(surah: SurahEntity, newState: Boolean) {
        surah.save = newState
        quranDao.updateSaveSurah(surah)
    }

    fun setSaveAyah(ayah: AyahEntity, newState: Boolean) {
        ayah.save = newState
        quranDao.updateSaveAyah(ayah)
    }
}