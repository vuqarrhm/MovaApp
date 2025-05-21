package com.example.film.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.film.api.FilmServices
import com.example.film.model.FilmItem
import com.example.film.model.FilmResult
import com.example.film.repostory.FilmRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repostory: FilmRepostory
): ViewModel() {
    val movie= MutableLiveData<List<FilmResult>>()
    val topRate=MutableLiveData<List<FilmResult>>()
    val nowplaying=MutableLiveData<List<FilmResult>>()

    init {
        getAllMovie("cc5ac8421cbcea4f38d07f5612f35feb")
        getTopRated("cc5ac8421cbcea4f38d07f5612f35feb")
        getNowPlaying("cc5ac8421cbcea4f38d07f5612f35feb")
    }


    fun getAllMovie(apikey:String){
        viewModelScope.launch(Dispatchers.IO) {
            val result=repostory.getPopular(apikey)
            result.results?.let {
                movie.postValue(it.filterNotNull())
            }


        }
    }

    fun getTopRated(apikey: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result=repostory.getTopRated(apikey)
            result.results?.let {
                topRate.postValue(it.filterNotNull())

            }
        }
    }


    fun getNowPlaying(apikey: String){
        viewModelScope.launch(Dispatchers.IO) {
            val result=repostory.getNowPlayin(apikey)
            result.results?.let {
                nowplaying.postValue(it.filterNotNull())
            }
        }
    }
}


