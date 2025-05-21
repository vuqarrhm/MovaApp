package com.example.film.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.film.repostory.FilmRepostory
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val repostory: FilmRepostory

): ViewModel() {
    val userResgister= MutableLiveData<Boolean>()
    val errorMessage= MutableLiveData<String>()

    fun register(email:String,password:String){
        viewModelScope.launch {
            try {
                firebaseAuth.createUserWithEmailAndPassword(email,password).await()
                val result=repostory.register(email, password)
                userResgister.value=result.user?.uid!=null
            }catch (e:Exception){

            }
        }


    }
}