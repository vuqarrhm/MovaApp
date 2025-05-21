package com.example.film.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.film.databinding.ItemCastBinding
import com.example.film.model.Cast
import com.squareup.picasso.Picasso

class CastAdapter():RecyclerView.Adapter<CastAdapter.CastViewHolder>() {
    val list= arrayListOf<Cast>()

    inner class CastViewHolder(val itemCastBinding: ItemCastBinding):
        RecyclerView.ViewHolder(itemCastBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        var layout=ItemCastBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CastViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast=list[position]
        holder.itemCastBinding.apply {
            textView2.text=cast.name
            textView6.text=cast.knownForDepartment
            Picasso.get().load("https://image.tmdb.org/t/p/w500${cast.profilePath}").into(imageView9)

        }

    }
    fun updataList(newList:List<Cast>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}