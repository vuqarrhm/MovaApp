package com.example.film.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.film.R
import com.example.film.adapters.MovieAdapter
import com.example.film.adapters.SearchAdapter
import com.example.film.adapters.TrendAdapter
import com.example.film.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    lateinit var binding:FragmentSearchBinding
    val viewModel by viewModels<SearchViewModel>()

    private val searchAdapter=SearchAdapter()
    private val trendAdapter=TrendAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.rvTrend.adapter=trendAdapter
        binding.rv.adapter = searchAdapter


        viewModel.getTrends("cc5ac8421cbcea4f38d07f5612f35feb")

        binding.editTextText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (query.isNotEmpty()) {
                    binding.rv.visibility=View.VISIBLE
                    binding.rvTrend.visibility=View.GONE
                    viewModel.getSearch(query, "cc5ac8421cbcea4f38d07f5612f35feb")
                } else {
                   viewModel.getTrends("cc5ac8421cbcea4f38d07f5612f35feb")
                binding.rv.visibility=View.GONE
                binding.rvTrend.visibility=View.VISIBLE
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })



    }

    fun observeData(){
        viewModel.searchFilm.observe(viewLifecycleOwner){
            searchAdapter.updateList(it)
            Log.d("API_RESPONSE", "Gələn məlumat: ${it.size}")
        }

        viewModel.trends.observe(viewLifecycleOwner){
            trendAdapter.updateList(it)


        }


    }
}
