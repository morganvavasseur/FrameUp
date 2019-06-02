package com.example.amaze.interfaces

import retrofit2.http.GET

interface DietService {
    @GET("diets")
    fun getUserDiets()
}