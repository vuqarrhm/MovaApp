package com.example.film.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.film.repostory.FilmRepostory
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    val repostory: FilmRepostory,
    val firebaseAuth: FirebaseAuth

):ViewModel() {

    val userLogin=MutableLiveData<Boolean>()
    val error=MutableLiveData<String>()
    val progresbar=MutableLiveData<Boolean>()
    val isAuther=MutableLiveData<Boolean>()

//    fun login(email:String,password:String){
//        progresbar.value=true
//
//        viewModelScope.launch {
//            try {
//                userLogin.value=repostory.login(email,password).user?.uid!=null
//
//            }catch (e:Exception){
//                error.value="Xeta bas verdi"
//
//            }
//
//        }
//        progresbar.value=false
//
//        }
//
//    }


    fun login(email: String, password: String) {
        progresbar.value = true // Yüklənmə başladıqda göstər

        viewModelScope.launch(Dispatchers.IO) {
            try {
                userLogin.postValue(repostory.login(email, password).user?.uid != null)
            } catch (e: Exception) {
                error.postValue("Xəta baş verdi")
            } finally {
                progresbar.postValue(false) // UI yenilənməsini əmin et
            }
        }
    }











}


