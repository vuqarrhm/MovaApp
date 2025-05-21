package com.example.film.repostory

import androidx.lifecycle.LiveData
import com.example.film.api.FilmServices
import com.example.film.model.BasketItems
import com.example.film.model.FilmResult
import com.example.film.room.BasketDAO
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import retrofit2.http.Query
import javax.inject.Inject

class FilmRepostory @Inject constructor(
    val apiServices: FilmServices,
    val basketDAO: BasketDAO,
    val firebaseAuth: FirebaseAuth

) {

    suspend fun getPopular(apikey: String)=apiServices.getMovies(apikey)
    suspend fun getNowPlayin(apikey: String)=apiServices.getNowPlaying(apikey)
    suspend fun getTopRated(apikey: String)=apiServices.getTopRated(apikey)



    suspend fun insertBasket(basket:BasketItems)=basketDAO.insert(basket)
    suspend fun deleteBasket(id:Int)=basketDAO.delete(id)
    fun getALl():LiveData<List<BasketItems>> = basketDAO.getAll()


    suspend fun getAllCredits(moviewId:Int,apikey:String)=apiServices.getMovieCredits(moviewId,apikey)

    suspend fun getAllVideos(moviewId: Int,apikey: String)=apiServices.getAllVideos(moviewId,apikey)

    suspend fun getSimilar(moviewId: Int,apikey: String)=apiServices.getSimilar(moviewId,apikey)

    suspend fun getComments(moviewId: Int,apikey: String)=apiServices.getComments(moviewId,apikey)

    suspend fun getSearch(query: String,apikey: String)=apiServices.getSearch(query,apikey)

    suspend fun getTrends(apikey: String)=apiServices.getTrends(apikey)


   suspend fun login(email:String,password:String)=
       firebaseAuth.signInWithEmailAndPassword(email,password).await()


    suspend fun register(email:String,password:String)=
        firebaseAuth.createUserWithEmailAndPassword(email,password).await()





}