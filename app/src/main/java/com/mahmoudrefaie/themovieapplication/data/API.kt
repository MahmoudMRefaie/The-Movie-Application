package com.mahmoudrefaie.themovieapplication.data

import com.mahmoudrefaie.themovieapplication.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("popular?api_key=a5956e11fc7cf87dd184b612e36ca190&language=en-US&page=1/")
    public fun getMoviesName(@Query("title") title:String) : Call<List<Movie>>
}