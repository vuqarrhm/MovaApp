package com.example.film.util

import android.view.View

fun View.visibleItem(){
    this.visibility= View.VISIBLE
}

fun View.goneItem(){
    this.visibility= View.GONE
}