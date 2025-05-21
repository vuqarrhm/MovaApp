package com.example.film.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.film.model.FilmResult
import com.example.film.model.SearchResult
import com.example.film.model.TrendResult
import com.example.film.repostory.FilmRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val repostory: FilmRepostory
):ViewModel() {

    val searchFilm=MutableLiveData<List<SearchResult>>()
    val trends=MutableLiveData<List<TrendResult>>()

    fun getSearch(query: String,apikey:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response=repostory.getSearch(query, apikey)
            response.results?.let {
                searchFilm.postValue(it.filterNotNull())
            }
        }
    }

    fun getTrends(apikey: String){
        viewModelScope.launch(Dispatchers.IO) {
            val response=repostory.getTrends(apikey)
            response.results?.let {
                trends.postValue(it.filterNotNull())

            }

        }
    }

}