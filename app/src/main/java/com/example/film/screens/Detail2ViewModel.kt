package com.example.film.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.film.model.Cast
import com.example.film.model.CommentResult
import com.example.film.model.FilmResult
import com.example.film.model.SimilarResult
import com.example.film.model.VideoResult
import com.example.film.repostory.FilmRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class Detail2ViewModel@Inject constructor(
    val repostory: FilmRepostory
):ViewModel() {
    val credit=MutableLiveData<List<Cast>>()
    val allVideos = MutableLiveData<List<VideoResult>>()
    val similar=MutableLiveData<List<SimilarResult>>()
    val comments= MutableLiveData<List<CommentResult>>()

    fun getAllCredits(movieID:Int,apikey:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response=repostory.getAllCredits(movieID,apikey)
            response.cast?.let {
                credit.postValue(it.filterNotNull())
            }
        }
    }


    fun getAllVideos(movieID: Int, apikey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repostory.getAllVideos(movieID, apikey)
            result?.results?.let {
                allVideos.postValue(it.filterNotNull())
            }
        }
    }

    fun getSimilar(movieID: Int,apikey: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result=repostory.getSimilar(movieID,apikey)
            result?.results?.let {
                similar.postValue(it.filterNotNull())

            }
        }
    }
    fun getComments(movieID: Int,apikey: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result=repostory.getComments(movieID,apikey)
            result?.results?.let {
                comments.postValue(it.filterNotNull())

            }
        }
    }

}