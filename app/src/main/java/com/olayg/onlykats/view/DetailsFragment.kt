package com.olayg.onlykats.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.olayg.onlykats.R
import com.olayg.onlykats.databinding.FragmentDetailBinding
import com.olayg.onlykats.util.loadWithGlide

class DetailsFragment : Fragment(R.layout.fragment_detail) {
    val breed: DetailsFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailBinding.inflate(layoutInflater, container, false).also {
        _binding = it
        Log.d("DETAILS FRAG!", "$breed")

    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val breed: DetailsFragmentArgs = breed
        with(binding) {
            breedName.text = breed.breed.name
            val image = breed.breed.imageUrl
            val adaption = breed.breed.adaptability
            val affection = breed.breed.affectionLevel
            val energy = breed.breed.energyLevel
            val intelligence = breed.breed.intelligence
            val shedding = breed.breed.sheddingLevel
            val life = breed.breed.lifeSpan
            breedImage.loadWithGlide(image.toString())
            setRatings(adaption, affection, intelligence, energy, shedding, life)
            webNav.text = breed.breed.wikipediaUrl
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setRatings (adaption: Int?, affection: Int?, intelligence: Int?, energy: Int?, shedding: Int?, life: String?) {
        if (adaption != null) {
            binding.firstSlider.value = adaption.toFloat()
        }
        if (affection != null) {
            binding.secondSlider.value = affection.toFloat()
        }
        if (intelligence != null) {
            binding.thirdSlider.value = intelligence.toFloat()
        }
        if (energy != null) {
            binding.fourthSlider.value = energy.toFloat()
        }
        if (shedding != null) {
            binding.fifthSlider.value = shedding.toFloat()
        }
        if (life != null) {
            binding.sixthSlider.text = life
        }
    }
}