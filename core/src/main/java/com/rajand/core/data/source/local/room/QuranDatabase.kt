package com.rajand.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rajand.core.data.source.local.entity.AyahEntity
import com.rajand.core.data.source.local.entity.SurahEntity

@Database(entities = [SurahEntity::class, AyahEntity::class], version = 1, exportSchema = false)
abstract class QuranDatabase : RoomDatabase() {

    abstract fun quranDao(): QuranDao
}