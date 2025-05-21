package com.example.film.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.film.databinding.ItemTrendBinding
import com.example.film.model.FilmResult
import com.example.film.model.TrendResponse
import com.example.film.model.TrendResult
import com.squareup.picasso.Picasso

class TrendAdapter():RecyclerView.Adapter<TrendAdapter.TrendViewHolder>() {
    val list= arrayListOf<TrendResult>()
    inner class TrendViewHolder(val itemTrendBinding: ItemTrendBinding):
        RecyclerView.ViewHolder(itemTrendBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val binding=ItemTrendBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TrendViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        val trend=list[position]
        Picasso.get().load("https://image.tmdb.org/t/p/w500${trend.posterPath}").into(holder.itemTrendBinding.imageViewPoster)


    }
    fun updateList(newList:List<TrendResult>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()

}
}