package com.example.film.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.film.R
import com.example.film.databinding.ItemMovieBinding
import com.example.film.model.BasketItems
import com.example.film.model.FilmResult
import com.example.film.screens.HomeFragmentDirections
import com.squareup.picasso.Picasso


class MovieAdapter(
    private val onItemClick:(BasketItems) ->Unit
): RecyclerView.Adapter<MovieAdapter.MoviewViewHolder>() {
    val list= arrayListOf<FilmResult>()

    inner class MoviewViewHolder(val itemMovieBinding: ItemMovieBinding): RecyclerView.ViewHolder(itemMovieBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviewViewHolder {
        val layout= ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MoviewViewHolder(layout)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MoviewViewHolder, position: Int) {
        val movie=list[position]
        if (!movie.posterPath.isNullOrEmpty()) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(holder.itemMovieBinding.imageView)
        } else {
            holder.itemMovieBinding.imageView.setImageResource(R.drawable.img_21) // 🔥 Əgər şəkil yoxdursa, boş şəkil göstər
        }


        holder.itemMovieBinding.imageViewBAsket.setOnClickListener {
            val basket=BasketItems(
            releaseDate = movie.releaseDate,
            video = movie.video,
            originalLanguage = movie.originalLanguage,
            overview = movie.overview,
            posterPath = movie.posterPath,
            title = movie.title ?: "",
            voteAverage = movie.voteAverage,
            popularity = movie.popularity
            )
            onItemClick(basket)
        }

        holder.itemMovieBinding.imageView.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie))
        }











    }


    fun updateList(newList:List<FilmResult>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}