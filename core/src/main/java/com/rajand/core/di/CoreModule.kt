package com.rajand.core.di

import androidx.room.Room
import com.rajand.core.data.QuranRepository
import com.rajand.core.data.source.local.LocalDataSource
import com.rajand.core.data.source.local.room.QuranDatabase
import com.rajand.core.data.source.remote.RemoteDataSource
import com.rajand.core.data.source.remote.network.ApiService
import com.rajand.core.domain.repository.IQuranRepository
import com.rajand.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<QuranDatabase>().quranDao()
    }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("rajand".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            QuranDatabase::class.java, "Quran.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.quran.sutanlab.id"
        val certicatePinenr = CertificatePinner.Builder()
            .add(hostname, "sha256/VLM9TxvFha6FJL9w6zpqfqBaksYI2Q24dv8DoF+WSr4=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certicatePinenr)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.quran.sutanlab.id/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single {
        LocalDataSource(get())
    }
    single {
        RemoteDataSource(get())
    }
    factory {
        AppExecutors()
    }
    single<IQuranRepository> {
        QuranRepository(
            get(),
            get(),
            get()
        )
    }
}