package com.example.film.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.film.databinding.ItemVideoBinding
import com.example.film.model.VideoResult
import com.squareup.picasso.Picasso

class VideoAdapter():RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    private val list= arrayListOf<VideoResult>()
    inner class VideoViewHolder(val itemVideoBinding: ItemVideoBinding):RecyclerView.ViewHolder(itemVideoBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val layout=ItemVideoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VideoViewHolder(layout)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video=list[position]
        holder.itemVideoBinding.textView5.text=video.name
        holder.itemVideoBinding.textView7.text=video.type
//        Picasso.get().load("https://img.youtube.com/vi/${video.key}/0.jpg").into(holder.itemVideoBinding.imageView11)
//        val thumbnailUrl = "https://img.youtube.com/vi/${video.key}/0.jpg"





        // Video kliklənəndə YouTube linkini açir
            video.key?.let { key ->
                val html = """
            <html>
            <body style="margin:0;padding:0;">
                <iframe width="100%" height="100%"
                    src="https://www.youtube.com/embed/$key"
                    frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
                    allowfullscreen>
                </iframe>
            </body>
            </html>
        """.trimIndent()

                val webView = holder.itemVideoBinding.webView
                webView.settings.javaScriptEnabled = true
                webView.settings.loadWithOverviewMode = true
                webView.settings.useWideViewPort = true


                holder.itemVideoBinding.imageThumb.visibility = View.VISIBLE
                holder.itemVideoBinding.webView.visibility = View.GONE

                val thumbnailUrl = "https://img.youtube.com/vi/${video.key}/0.jpg"
                Picasso.get().load(thumbnailUrl).into(holder.itemVideoBinding.imageThumb)

                holder.itemVideoBinding.imageThumb.setOnClickListener {
                    holder.itemVideoBinding.imageThumb.visibility = View.GONE
                    holder.itemVideoBinding.webView.visibility = View.VISIBLE
                    webView.loadData(html, "text/html", "utf-8")

                }


            }



    }

    fun updateList(newList:List<VideoResult>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}