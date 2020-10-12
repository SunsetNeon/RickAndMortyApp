package com.android.rickandmortyapp

import com.android.rickandmortyapp.api.RMapiJSON
import retrofit2.http.GET

interface APIRequest {

    @GET("character/")

    suspend fun getAllCharacters() : RMapiJSON
}