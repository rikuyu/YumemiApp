package com.example.yumemiapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yumemiapp.databinding.FragmentHomeBinding
import com.example.yumemiapp.model.data.Profile
import com.example.yumemiapp.ui.adapter.HomeAdapter
import com.example.yumemiapp.util.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    lateinit var adapter: HomeAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.contributers.observe(viewLifecycleOwner, { response ->
            showProgressbar()
            when (response) {
                is State.Success -> {
                    hideProgressbar()
                    response.data?.let { it ->
                        adapter = HomeAdapter(requireContext(), it)
                        binding.recyclerView.adapter = adapter
                        binding.recyclerView.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        binding.recyclerView.addItemDecoration(
                            DividerItemDecoration(
                                requireContext(),
                                LinearLayoutManager(requireContext()).orientation
                            )
                        )

                        adapter.setOnItemClickListener(object :
                            HomeAdapter.OnItemClickListener {
                            override fun onDetailClickListener(profile: Profile) {
                                findNavController().navigate(
                                    HomeFragmentDirections
                                        .actionHomeFragmentToDetailFragment(profile)
                                )
                            }

                            override fun onFavoriteClickListener(profile: Profile) {
                                viewModel.insertContributer(profile)
                                Toast.makeText(requireContext(), "いいね", Toast.LENGTH_LONG).show()
                            }
                        })
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