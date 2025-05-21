package com.example.film.model


import com.google.gson.annotations.SerializedName

data class FilmItem(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<FilmResult?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)