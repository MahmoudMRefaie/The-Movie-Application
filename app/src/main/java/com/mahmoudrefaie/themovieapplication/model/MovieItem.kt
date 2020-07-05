package com.mahmoudrefaie.themovieapplication.model

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("title")
    var title:String?=null,
    @SerializedName("overview")
    var overview:String?=null,
    @SerializedName("release_date")
    var releaseDate:String?=null,
    @SerializedName("original_language")
    var originalLanguage:String?=null,
    @SerializedName("poster_path")
    var posterPath:String?=null,
    @SerializedName("vote_average")
    var vote:Double?=0.0,
    @SerializedName("adult")
    var adult:Boolean?=false

)