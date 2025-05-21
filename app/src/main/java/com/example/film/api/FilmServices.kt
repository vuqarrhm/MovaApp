package com.example.film.api

import com.example.film.model.CommentResponse
import com.example.film.model.CreditResponse
import com.example.film.model.FilmItem
import com.example.film.model.SearchResponse
import com.example.film.model.SimilarResponse
import com.example.film.model.TrendResponse
import com.example.film.model.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmServices {
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String
    ): FilmItem

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String
    ):FilmItem

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String
    ):FilmItem

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): CreditResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getAllVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String

    ):VideoResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilar(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ):SimilarResponse



    @GET("movie/{movie_id}/reviews")
    suspend fun getComments(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ):CommentResponse

    @GET("search/movie")
    suspend fun getSearch(
        @Query("query")query: String,
        @Query("api_key")apiKey: String
    ):SearchResponse


    @GET("trending/movie/day")
    suspend fun getTrends(
        @Query("api_key") apiKey: String
    ): TrendResponse






}


