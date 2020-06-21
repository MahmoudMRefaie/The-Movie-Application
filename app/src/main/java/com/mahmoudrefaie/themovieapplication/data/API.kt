package com.mahmoudrefaie.themovieapplication.data

import com.mahmoudrefaie.themovieapplication.model.MovieItem
import com.mahmoudrefaie.themovieapplication.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {
    @GET("list/{id}")
    public fun getMoviesName(
        @Path("id") id:Int,
        @Query("api_key") api_key:String

    ) : Call<MovieResponse>
}