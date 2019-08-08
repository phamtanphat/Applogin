package com.example.applogin.api

import com.example.applogin.model.Linkedin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRepository {
    @GET("me?projection=(id,firstName,lastName,profilePicture(displayImage~:playableStreams))")
    fun login() : Call<Linkedin>
}