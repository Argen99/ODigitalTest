package com.example.data.utils

interface DataMapper<out T> {
    fun toDomain(): T
}