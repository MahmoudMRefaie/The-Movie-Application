package com.mahmoudrefaie.themovieapplication.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("created_by")
    val createdBy: String? = null,
    @SerializedName("description")
    val desc: String? = null,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("items")
    val items: List<MovieItem>? = null,
    val totalResults: Int? = null
)