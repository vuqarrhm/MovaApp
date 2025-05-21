package com.example.film.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.film.R
import com.example.film.adapters.OnboardingAdapter
import com.example.film.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {
    private lateinit var adapter: OnboardingAdapter
    lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentOnboardingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf(
            Triple(R.drawable.filmposter, "Discover the Magic of Movies", "Explore a vast collection of thrilling blockbusters, indie gems, and timeless classics—all in one place!"),
            Triple(R.drawable.filmposter, "Watch Anytime, Anywhere", "Stream your favorite films on your device, whether you're at home or on the go. Enjoy seamless playback!"),
            Triple(R.drawable.filmposter, "Connect & Share the Experience", "Create watchlists, rate movies, and share reviews with friends. Your cinema adventure starts here!")
        )

        adapter = OnboardingAdapter(items)
        binding.viewpager.adapter = adapter  // Əvvəlcə adapteri təyin edirik
        binding.dotsIndicator.attachTo(binding.viewpager)  // Sonra indicatoru bağlayırıq


        binding.button2.setOnClickListener {
            val nextPage = binding.viewpager.currentItem + 1
            if (nextPage < adapter.itemCount) {
                binding.viewpager.currentItem = nextPage
            } else {
                findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
            }
        }


    }
   }