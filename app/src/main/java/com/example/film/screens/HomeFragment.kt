package com.example.film.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.film.adapters.MovieAdapter
import com.example.film.adapters.NowplayAdapter
import com.example.film.adapters.TopratedAdapter
import com.example.film.databinding.FragmentHomeBinding
import com.example.film.model.BasketItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var movieAdapter:MovieAdapter
    private lateinit var topratedAdapter:TopratedAdapter
    private val nowplayAdapter=NowplayAdapter()
    private val viewModel by viewModels<HomeViewModel>()
    private val basketViewModel by viewModels<BasketViewModel>()
    private var basketList= listOf<BasketItems>()

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()


        binding.viewpager.adapter=nowplayAdapter
        binding.dotsIndicator.attachTo(binding.viewpager)




       basketViewModel.getAll().observe(viewLifecycleOwner){basket->
           basketList=basket
       }

        movieAdapter = MovieAdapter { item ->
            val alreadyExists = basketList.any { it.title == item.title }
            if (!alreadyExists) {
                val basket = BasketItems(
                    releaseDate = item.releaseDate,
                    video = item.video,
                    originalLanguage = item.originalLanguage,
                    overview = item.overview,
                    posterPath = item.posterPath,
                    title = item.title ?: "",
                    voteAverage = item.voteAverage,
                    popularity = item.popularity
                )
                basketViewModel.addItem(basket)
            }
        }


        topratedAdapter= TopratedAdapter { item->
            val alreadyExists = basketList.any { it.title == item.title }
            if (!alreadyExists){
                val basket = BasketItems(
                    releaseDate = item.releaseDate,
                    video = item.video,
                    originalLanguage = item.originalLanguage,
                    overview = item.overview,
                    posterPath = item.posterPath,
                    title = item.title ?: "",
                    voteAverage = item.voteAverage,
                    popularity = item.popularity
                )
                basketViewModel.addItem(basket)
            }
        }


        binding.rvTop10.adapter=topratedAdapter
        binding.rvNewReleases.adapter=movieAdapter







    }

    fun observeData(){
        viewModel.movie.observe(viewLifecycleOwner){movies->
            movieAdapter.updateList(movies)
        }

        viewModel.topRate.observe(viewLifecycleOwner){toprated->
            topratedAdapter.updateList(toprated)

        }
        viewModel.nowplaying.observe(viewLifecycleOwner){nowplay->
            nowplayAdapter.updateList(nowplay)

        }
    }

}
