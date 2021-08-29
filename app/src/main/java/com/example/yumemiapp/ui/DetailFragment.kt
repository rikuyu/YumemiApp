package com.example.yumemiapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.yumemiapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDetailInfo()

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(
                DetailFragmentDirections
                    .actionDetailFragmentToHomeFragment()
            )
        }


        binding.buttonFollowing.setOnClickListener {
            findNavController().navigate(
                DetailFragmentDirections
                    .actionDetailFragmentToFollowingFragment(args.profileInfo.name)
        }
        
        binding.github.setOnClickListener {
            findNavController().navigate(
                DetailFragmentDirections
                    .actionDetailFragmentToGithubFragment(args.profileInfo.html_url)
            )
        }
    }

    private fun loadDetailInfo(){
        binding.contributerName.text = args.profileInfo.name
        Glide.with(this).load(args.profileInfo.avatar_url).into(binding.contributerIcon)
    }
}