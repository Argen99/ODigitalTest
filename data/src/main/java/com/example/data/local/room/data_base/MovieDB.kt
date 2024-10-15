package com.example.data.local.room.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.room.dao.FavoritesDao
import com.example.data.local.room.dao.MovieDao
import com.example.data.local.room.dao.RemoteKeysDao
import com.example.data.local.room.entity.MovieEntity
import com.example.data.local.room.entity.FavoriteMovieEntity
import com.example.data.local.room.entity.RemoteKeysEntity

@Database(entities = [FavoriteMovieEntity::class, MovieEntity::class, RemoteKeysEntity::class], version = 1)
abstract class MovieDB: RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
    abstract fun getFavoritesDao(): FavoritesDao
}