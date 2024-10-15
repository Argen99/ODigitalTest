package com.example.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
        const val TOKEN= "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjOGQ5NWNmMTkzZDA4MjQzMDRjZThiMWIzZmE2OGFlNyIsIm5iZiI6MTcyODkwNTI0Ni4yMDgyODUsInN1YiI6IjY3MGNmZDA4NGRmNTlhNjA4YzYzOWJhYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.u_jZ7EkMTccV5r-kQEkrqojTR-NEEb6H_5MaUE9htgM"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader(name = HEADER_AUTHORIZATION, value ="$TOKEN_TYPE $TOKEN")
        return chain.proceed(request.build())
    }
}