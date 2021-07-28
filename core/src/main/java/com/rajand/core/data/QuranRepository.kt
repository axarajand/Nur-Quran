package com.rajand.core.data

import com.rajand.core.data.source.local.LocalDataSource
import com.rajand.core.data.source.remote.RemoteDataSource
import com.rajand.core.data.source.remote.network.ApiResponse
import com.rajand.core.data.source.remote.response.DataItem
import com.rajand.core.data.source.remote.response.VersesItem
import com.rajand.core.domain.model.Ayah
import com.rajand.core.domain.model.Surah
import com.rajand.core.domain.repository.IQuranRepository
import com.rajand.core.utils.AppExecutors
import com.rajand.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuranRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
) : IQuranRepository {

    override fun getAllSurah(): Flow<Resource<List<Surah>>> =
         object : NetworkBoundResource<List<Surah>, List<DataItem>>() {
            override fun loadFromDB(): Flow<List<Surah>> {
                return localDataSource.getAllSurah().map {
                    DataMapper.surahMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Surah>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<DataItem>>> =
                remoteDataSource.getAllSurah()

            override suspend fun saveCallResult(data: List<DataItem>) {
                val surahList = DataMapper.surahMapResponsesToEntities(data)
                localDataSource.insertSurah(surahList)
            }
        }.asFlow()

    override fun getAllAyah(numberSurah: Int): Flow<Resource<List<Ayah>>> =
         object : NetworkBoundResource<List<Ayah>, List<VersesItem>>() {
            override fun loadFromDB(): Flow<List<Ayah>> {
                return localDataSource.getAllAyah(numberSurah).map {
                    DataMapper.ayahMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Ayah>?): Boolean =
                true

             override suspend fun createCall(): Flow<ApiResponse<List<VersesItem>>> =
                remoteDataSource.getAllAyah(numberSurah)

            override suspend fun saveCallResult(data: List<VersesItem>) {
                val ayahList = DataMapper.ayahMapResponsesToEntities(numberSurah, data)
                localDataSource.insertAyah(ayahList)
            }
        }.asFlow()

    override fun getSaveSurah(): Flow<List<Surah>> {
        return localDataSource.getSaveSurah().map {
            DataMapper.surahMapEntitiesToDomain(it)
        }
    }

    override fun setSaveSurah(surah: Surah, state: Boolean) {
        val surahEntity = DataMapper.surahMapDomainToEntity(surah)
        appExecutors.diskIO().execute { localDataSource.setSaveSurah(surahEntity, state) }
    }

    override fun setSaveAyah(ayah: Ayah, state: Boolean) {
        val ayahEntity = DataMapper.ayahMapDomainToEntity(ayah)
        appExecutors.diskIO().execute { localDataSource.setSaveAyah(ayahEntity,state) }
    }
}