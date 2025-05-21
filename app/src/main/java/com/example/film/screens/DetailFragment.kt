package com.example.film.screens

import android.animation.ObjectAnimator
import android.graphics.Color
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
import com.example.film.databinding.FragmentDetailBinding
import com.example.film.model.Cast
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    lateinit var binding:FragmentDetailBinding
    private val castAdapter=CastAdapter()
    private val videoAdapter=VideoAdapter()
    private val similarAdapter=SimilarAdapter()
    private val commentAdapter=CommentAdapter()
    private val viewModel by viewModels<DetailViewModel>()

    val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
        args.id?.id?.let {id:Int->
            viewModel.getAllCredits(id,"cc5ac8421cbcea4f38d07f5612f35feb")
        }
        args.id?.id?.let {id:Int->
            viewModel.getAllVideos(id,"cc5ac8421cbcea4f38d07f5612f35feb")
        }
        args.id?.id?.let {id:Int->
            viewModel.getSimilar(id,"cc5ac8421cbcea4f38d07f5612f35feb")
        }
        args.id.id?.let {id:Int->
            viewModel.getComments(id,"cc5ac8421cbcea4f38d07f5612f35feb")
        }

        binding.rvVideo.adapter=videoAdapter
        binding.rvCast.adapter=castAdapter
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
            textViewName.text=args.id.title
            textViewMetn.text=args.id.overview
            textViewrate.text=args.id.voteAverage.toString()
            textViewDate.text=args.id.releaseDate
            textViewLang.text=args.id.originalLanguage
            binding.ratingBar.rating = (args.id.voteAverage?.toFloat() ?: 0f) / 10f
            binding.ratingBar.invalidate()  // UI-ni yenilə
            binding.ratingBar.requestLayout()  // Yeni görünüş tətbiq et
            binding.textViewrate.text = String.format("%.1f", args.id.voteAverage)

            if (!args.id.backdropPath.isNullOrEmpty()) {
                Picasso.get().load("https://image.tmdb.org/t/p/w500${args.id.backdropPath}")
                    .into(imageView7)
            } else {
               binding.imageView7.setImageResource(R.drawable.img_21) // 🔥 Əgər şəkil yoxdursa, boş şəkil göstər
            }
        }

    }


    fun observeData(){
        viewModel.credit.observe(viewLifecycleOwner){cast->
            castAdapter.updataList(cast)
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




