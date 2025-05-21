package com.example.film.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.film.databinding.ItemSimilarBinding
import com.example.film.model.FilmResult
import com.example.film.model.SimilarResult
import com.squareup.picasso.Picasso

class SimilarAdapter():RecyclerView.Adapter<SimilarAdapter.SimilarViewHolder>() {
    val list= arrayListOf<SimilarResult>()
    inner class SimilarViewHolder(val itemSimilarBinding: ItemSimilarBinding):RecyclerView.ViewHolder(itemSimilarBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        val layout=ItemSimilarBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SimilarViewHolder(layout)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        val similar=list[position]
        holder.itemSimilarBinding.textView8.text=String.format("%.1f", similar.voteAverage)
        Picasso.get().load("https://image.tmdb.org/t/p/w500${similar.posterPath}")
            .into(holder.itemSimilarBinding.imageView10)
    }

    fun updateList(newList:List<SimilarResult>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}