package com.example.film.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.film.model.BasketItems
import com.example.film.repostory.FilmRepostory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel@Inject constructor(
    val repostory: FilmRepostory
):ViewModel() {

    fun getAll():LiveData<List<BasketItems>> = repostory.getALl()

     fun addItem(basket:BasketItems){
         viewModelScope.launch(Dispatchers.IO) {
           repostory.insertBasket(basket)
         }
    }

    fun deleteItem(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repostory.deleteBasket(id)
        }
    }

}