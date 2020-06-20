package com.mahmoudrefaie.themovieapplication.data

import com.mahmoudrefaie.themovieapplication.model.MovieItem
import com.mahmoudrefaie.themovieapplication.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("list/3")
    public fun getMoviesName(
        @Query("api_key") api_key:String
    ) : Call<MovieResponse>
}