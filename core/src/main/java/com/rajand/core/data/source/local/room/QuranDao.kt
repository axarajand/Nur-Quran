package com.rajand.core.data.source.local.room

import androidx.room.*
import com.rajand.core.data.source.local.entity.AyahEntity
import com.rajand.core.data.source.local.entity.SurahEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuranDao {

    @Query("SELECT * FROM surah ORDER BY numberSurah ASC")
    fun getAllSurah(): Flow<List<SurahEntity>>

    @Query("SELECT * FROM ayah WHERE numberSurah = :numberSurah ORDER BY numberAyah ASC")
    fun getAllAyah(numberSurah: Int): Flow<List<AyahEntity>>

    @Query("SELECT * FROM surah WHERE save = 1")
    fun getSaveSurah(): Flow<List<SurahEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurah(surah: List<SurahEntity>)

    @Update
    fun updateSaveSurah(surah: SurahEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAyah(ayah: List<AyahEntity>)

    @Update
    fun updateSaveAyah(ayah: AyahEntity)
}