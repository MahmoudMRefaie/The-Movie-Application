package com.mahmoudrefaie.themovieapplication.model

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("title")
    var title:String?=null,
    @SerializedName("overview")
    var overview:String?=null,
    @SerializedName("id")
    var id:Int?=0,
    @SerializedName("original_language")
    var originalLanguage:String?=null
)