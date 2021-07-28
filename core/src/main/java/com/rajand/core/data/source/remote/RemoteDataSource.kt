package com.rajand.core.data.source.remote

import android.util.Log
import com.rajand.core.data.source.remote.network.ApiResponse
import com.rajand.core.data.source.remote.network.ApiService
import com.rajand.core.data.source.remote.response.DataItem
import com.rajand.core.data.source.remote.response.VersesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllSurah(): Flow<ApiResponse<List<DataItem>>> {
        return flow {
            try {
                val response = apiService.getAllSurah()
                val dataArray = response.data
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllAyah(numberSurah: Int): Flow<ApiResponse<List<VersesItem>>> {
        return flow {
            try {
                val response = apiService.getAllAyah(numberSurah)
                val data = response.data
                val dataArray = data.verses
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}