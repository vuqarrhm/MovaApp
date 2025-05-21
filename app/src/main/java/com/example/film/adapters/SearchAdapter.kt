package com.example.film.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.film.R
import com.example.film.databinding.ItemSearchBinding
import com.example.film.model.FilmResult
import com.example.film.model.SearchResult
import com.squareup.picasso.Picasso

class SearchAdapter():RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    private val list= arrayListOf<SearchResult>()
    inner class SearchViewHolder(val itemSearchBinding: ItemSearchBinding):
        RecyclerView.ViewHolder(itemSearchBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layout=ItemSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(layout)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val search=list[position]
        if (!search.posterPath.isNullOrEmpty()) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500${search.posterPath}")
                .into(holder.itemSearchBinding.imageViewPoster)
        } else {
            holder.itemSearchBinding.imageViewPoster.setImageResource(R.drawable.img_21)
        }
    }

    fun updateList(newList:List<SearchResult>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}