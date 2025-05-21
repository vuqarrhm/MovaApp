package com.example.film.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.film.R
import com.example.film.databinding.ItemNowplayBinding
import com.example.film.model.FilmResult
import com.squareup.picasso.Picasso

class NowplayAdapter():RecyclerView.Adapter<NowplayAdapter.NowplayViewHolder>() {
    val list= arrayListOf<FilmResult>()

    inner class NowplayViewHolder(val itemNowplayBinding: ItemNowplayBinding):RecyclerView.ViewHolder(itemNowplayBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowplayViewHolder {
        val layout=ItemNowplayBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NowplayViewHolder(layout)

    }


    override fun getItemCount(): Int {
        return minOf(list.size, 3)
    }


    override fun onBindViewHolder(holder: NowplayViewHolder, position: Int) {
        val nowplay=list[position]
        holder.itemNowplayBinding.textView.text=nowplay.title

        if (!nowplay.backdropPath.isNullOrEmpty()) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500${nowplay.backdropPath}")
                .into(holder.itemNowplayBinding.imageView3)
        } else {
            holder.itemNowplayBinding.imageView3.setImageResource(R.drawable.img_21)
        }
    }

    fun updateList(newList:List<FilmResult>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

}