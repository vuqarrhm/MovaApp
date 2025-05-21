package com.example.film.screens

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.film.R
import com.example.film.adapters.CastAdapter
import com.example.film.adapters.CommentAdapter
import com.example.film.adapters.SimilarAdapter
import com.example.film.adapters.VideoAdapter
import com.example.film.databinding.FragmentDetail2Binding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment2 : Fragment() {
    lateinit var binding:FragmentDetail2Binding
    private val castAdapter=CastAdapter()
    private val videoAdapter= VideoAdapter()
    private val similarAdapter= SimilarAdapter()
    private val commentAdapter= CommentAdapter()
    private val viewModel by viewModels<Detail2ViewModel>()

    val args: DetailFragment2Args by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDetail2Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

        args.id2?.id?.let { id2:Int->
            viewModel.getAllCredits(id2,"cc5ac8421cbcea4f38d07f5612f35feb")
        }

        args.id2?.id?.let {id:Int->
            viewModel.getAllVideos(id,"cc5ac8421cbcea4f38d07f5612f35feb")
        }
        args.id2?.id?.let {id:Int->
            viewModel.getSimilar(id,"cc5ac8421cbcea4f38d07f5612f35feb")
        }
        args.id2.id?.let {id:Int->
            viewModel.getComments(id,"cc5ac8421cbcea4f38d07f5612f35feb")
        }

        binding.rvVideo.adapter=videoAdapter
        binding.rvCast2.adapter=castAdapter
        binding.rvSimilar.adapter=similarAdapter
        binding.rvComment.adapter=commentAdapter


        binding.rvSimilar.visibility=View.GONE
        binding.rvComment.visibility=View.GONE
        binding.rvVideo.visibility=View.VISIBLE

        val tabTrailers = binding.textViewTrailer
        val tabMore = binding.textViewMore
        val tabComments = binding.textViewComments
        val redIndicator = binding.tabIndicator

        val tabs = listOf(tabTrailers, tabMore, tabComments)


        // Tab rəngi yeniləyən funksiya
        fun setActiveTab(selectedTab: TextView) {
            tabs.forEach { tab ->
                tab.setTextColor(
                    if (tab == selectedTab)
                        ContextCompat.getColor(requireContext(), android.R.color.holo_red_light)
                    else
                        ContextCompat.getColor(requireContext(), android.R.color.darker_gray)
                )
            }
        }

        // İlk açılışda "More Like This" aktiv
        fun initIndicator() {
            tabTrailers.post {
                redIndicator.layoutParams.width = tabTrailers.width
                redIndicator.x = tabTrailers.x
                redIndicator.requestLayout()
                setActiveTab(tabTrailers)
            }
        }



        // Qırmızı xətti hərəkət etdir
        fun moveIndicatorTo(tab: TextView) {
            val animator = ObjectAnimator.ofFloat(redIndicator, "x", tab.x)
            animator.duration = 300
            animator.start()
            redIndicator.layoutParams.width = tab.width
            redIndicator.requestLayout()
            setActiveTab(tab)
        }

// Listener-lər
        tabTrailers.setOnClickListener {
            binding.rvVideo.visibility=View.VISIBLE
            binding.rvComment.visibility=View.GONE
            binding.rvSimilar.visibility=View.GONE
            moveIndicatorTo(tabTrailers)

        }
        tabMore.setOnClickListener {
            binding.rvVideo.visibility=View.GONE
            binding.rvComment.visibility=View.GONE
            binding.rvSimilar.visibility=View.VISIBLE
            moveIndicatorTo(tabMore)

        }
        tabComments.setOnClickListener {
            binding.rvVideo.visibility=View.GONE
            binding.rvSimilar.visibility=View.GONE
            binding.rvComment.visibility=View.VISIBLE
            moveIndicatorTo(tabComments)
        }

        // Açılışda aktiv et
        initIndicator()





        binding.apply {
            textViewName.text=args.id2.title
            textViewDate.text=args.id2.releaseDate
            textViewMetn.text=args.id2.overview
            textViewLang.text=args.id2.originalLanguage
            textViewrate.text=args.id2.voteAverage.toString()
            binding.ratingBar.rating = (args.id2.voteAverage?.toFloat() ?: 0f) / 15f
            binding.ratingBar.invalidate()  // UI-ni yenilə
            binding.ratingBar.requestLayout()  // Yeni görünüş tətbiq et
            binding.textViewrate.text = String.format("%.1f", args.id2.voteAverage)
            if (!args.id2.backdropPath.isNullOrEmpty()) {
                Picasso.get().load("https://image.tmdb.org/t/p/w500${args.id2.backdropPath}")
                    .into(imageView7)
            } else {
                binding.imageView7.setImageResource(R.drawable.img_21) // 🔥 Əgər şəkil yoxdursa, boş şəkil göstər
            }



        }
    }


    fun observeData(){
        viewModel.credit.observe(viewLifecycleOwner){
            castAdapter.updataList(it)
        }

        viewModel.allVideos.observe(viewLifecycleOwner){video->
            videoAdapter.updateList(video)
        }

        viewModel.similar.observe(viewLifecycleOwner){similar->
            similarAdapter.updateList(similar)
        }
        viewModel.comments.observe(viewLifecycleOwner){comments->
            commentAdapter.updateList(comments)

        }


    }

}
