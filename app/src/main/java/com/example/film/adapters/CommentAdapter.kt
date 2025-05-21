package com.example.film.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.film.R
import com.example.film.databinding.ItemCommentBinding
import com.example.film.model.CommentResult
import com.squareup.picasso.Picasso

class CommentAdapter():RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    val list= arrayListOf<CommentResult>()
    inner class CommentViewHolder(val itemCommentBinding: ItemCommentBinding):RecyclerView.ViewHolder(itemCommentBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layout=ItemCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(layout)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment=list[position]
        holder.itemCommentBinding.apply {
            textViewName.text=comment.authorDetails?.name
            textViewComments.text=comment.content
            var imageList = listOf(
                R.drawable.dog,
                R.drawable.dinosaur,
                R.drawable.koala,
                R.drawable.meerkat,
                R.drawable.panda
            )
            imageView11.setImageResource(imageList.random())



        }
    }

    fun updateList(newList:List<CommentResult>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}