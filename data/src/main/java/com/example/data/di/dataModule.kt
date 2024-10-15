package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.room.dao.FavoritesDao
import com.example.data.local.room.dao.MovieDao
import com.example.data.local.room.dao.RemoteKeysDao
import com.example.data.local.room.data_base.MovieDB
import com.example.data.remote.api_service.MovieApiService
import com.example.data.remote.interceptor.TokenInterceptor
import com.example.data.remote.repository.MovieRepositoryImpl
import com.example.domain.repository.MovieRepository
import com.example.data.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    singleOf(::provideOkHttpClient)
    singleOf(::provideRetrofit)
    singleOf(::provideMainApiService)
    singleOf(::MovieRepositoryImpl) {
        bind<MovieRepository>()
    }
    singleOf(::provideMovieDB)
    singleOf(::provideMovieDao)
    singleOf(::provideRemoteKeysDao)
    singleOf(::provideFavoritesDao)
}

fun provideOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val tokenInterceptor = TokenInterceptor()
    return OkHttpClient().newBuilder()
        .addInterceptor(tokenInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideMainApiService(retrofit: Retrofit): MovieApiService {
    return retrofit.create(MovieApiService::class.java)
}

fun provideMovieDB(context: Context): MovieDB {
    return Room.databaseBuilder(context, MovieDB::class.java, "database.db").build()
}

fun provideMovieDao(movieDb: MovieDB): MovieDao =
    movieDb.getMovieDao()


fun provideRemoteKeysDao(movieDb: MovieDB): RemoteKeysDao =
    movieDb.getRemoteKeysDao()

fun provideFavoritesDao(movieDb: MovieDB): FavoritesDao =
    movieDb.getFavoritesDao()
