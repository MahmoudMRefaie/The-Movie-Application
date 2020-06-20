package com.mahmoudrefaie.themovieapplication.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

public class RetrofitClient {
    private var BASE_URL = "https://api.themoviedb.org/3/"
    private var retrofit : Retrofit ? = null

    fun getClient() : Retrofit {
        if(retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun getMovieNameApi(): API? {
        return getClient().create(API::class.java)
    }

}