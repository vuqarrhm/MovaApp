package com.example.film.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.film.R
import com.example.film.databinding.ItemBasketBinding
import com.example.film.model.BasketItems
import com.squareup.picasso.Picasso

class BasketAdapter(
    private val onClickItem:(BasketItems)->Unit
):RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    val list= arrayListOf<BasketItems>()

    inner class BasketViewHolder(val itemBasketBinding: ItemBasketBinding):RecyclerView.ViewHolder(itemBasketBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val layout=ItemBasketBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BasketViewHolder((layout))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basket=list[position]
        holder.itemBasketBinding.apply {
            imageViewDelete.setOnClickListener {
                onClickItem(basket)
            }
            if (!basket.posterPath.isNullOrEmpty()){
                Picasso.get().load("https://image.tmdb.org/t/p/w500${basket.posterPath}").into(imageView6)
            }else{
                holder.itemBasketBinding.imageView6.setImageResource(R.drawable.img_21)
            }
        }

    }

    fun updateList(newList:List<BasketItems>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

}