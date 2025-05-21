package com.example.film.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.film.R
import com.example.film.adapters.BasketAdapter
import com.example.film.databinding.FragmentBasketBinding
import com.example.film.model.BasketItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {
    lateinit var binding:FragmentBasketBinding
    private val viewModel by viewModels<BasketViewModel>()
    private lateinit var basketAdapter: BasketAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentBasketBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        basketAdapter=BasketAdapter{
            viewModel.deleteItem(it.id)
        }
        obsrveData()
        binding.rv.adapter=basketAdapter



    }

    fun obsrveData(){
        viewModel.getAll().observe(viewLifecycleOwner){
            basketAdapter.updateList(it)

        }
    }



}
