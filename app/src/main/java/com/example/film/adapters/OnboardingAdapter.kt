package com.example.film.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.film.databinding.ItemOnboardBinding

class OnboardingAdapter(private val items: List<Triple<Int, String, String>>):RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    inner class OnboardingViewHolder(val itemOnboardBinding: ItemOnboardBinding):RecyclerView.ViewHolder(itemOnboardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val layout=ItemOnboardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OnboardingViewHolder(layout)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        val (imageRes, text,description) = items[position]
        holder.itemOnboardBinding.imageView3.setImageResource(imageRes)
        holder.itemOnboardBinding.textView.text=text
        holder.itemOnboardBinding.textView2.text=description


    }
}