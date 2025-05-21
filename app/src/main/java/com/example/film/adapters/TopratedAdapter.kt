package com.example.film.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.film.R
import com.example.film.databinding.ItemTopratedBinding
import com.example.film.model.BasketItems
import com.example.film.model.FilmResult
import com.example.film.screens.HomeFragmentDirections
import com.squareup.picasso.Picasso

class TopratedAdapter(
    val onItemClick:(BasketItems)->Unit

):RecyclerView.Adapter<TopratedAdapter.TopratedViewHolder>() {
    val list= arrayListOf<FilmResult>()
    inner class TopratedViewHolder(val itemTopratedBinding: ItemTopratedBinding):
        RecyclerView.ViewHolder(itemTopratedBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopratedViewHolder {
        val layout=ItemTopratedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TopratedViewHolder(layout)

    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: TopratedViewHolder, position: Int) {
        var toprated=list[position]
        if (!toprated.posterPath.isNullOrEmpty()) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500${toprated.posterPath}")
                .into(holder.itemTopratedBinding.imageView2)
        } else {
            holder.itemTopratedBinding.imageView2.setImageResource(R.drawable.img_21)
        }

        holder.itemTopratedBinding.imageViewSave.setOnClickListener {

            val basket=BasketItems(
                releaseDate = toprated.releaseDate,
                video = toprated.video,
                originalLanguage = toprated.originalLanguage,
                overview = toprated.overview,
                posterPath = toprated.posterPath,
                title = toprated.title ?: "",
                voteAverage = toprated.voteAverage,
                popularity = toprated.popularity
            )
            onItemClick(basket)

        }

        holder.itemTopratedBinding.imageView2.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment2(toprated))
        }


    }

    fun updateList(newList:List<FilmResult>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}