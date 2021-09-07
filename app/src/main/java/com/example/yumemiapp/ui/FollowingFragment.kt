package com.example.yumemiapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yumemiapp.databinding.FragmentFollowingBinding
import com.example.yumemiapp.ui.adapter.FollowingAdapter
import com.example.yumemiapp.util.State

class FollowingFragment : Fragment() {

    private val args: FollowingFragmentArgs by navArgs()
    private val viewModel: MainViewModel by activityViewModels()
    lateinit var adapter: FollowingAdapter

    private var _binding: FragmentFollowingBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userName = args.userName
        viewModel.getFollowing(userName)
        observeLiveData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeLiveData(){
        viewModel.followings.observe(viewLifecycleOwner, { response ->
            showProgressbar()
            when (response) {
                is State.Success -> {
                    hideProgressbar()
                    response.data?.let { it ->
                        adapter = FollowingAdapter(requireContext(), it)
                        binding.recyclerView.adapter = adapter
                        binding.recyclerView.layoutManager =
                            GridLayoutManager(requireContext(), 3, RecyclerView.VERTICAL, false)
                    }
                }
                is State.Error -> {
                    response.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    }
                }
                is State.Loading -> {
                    showProgressbar()
                }
            }
        })
    }


    private fun showProgressbar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
        binding.progressBar.visibility = View.INVISIBLE
    }
}