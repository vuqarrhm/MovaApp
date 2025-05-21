package com.example.film.model


import com.google.gson.annotations.SerializedName

data class SimilarResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<SimilarResult?>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)