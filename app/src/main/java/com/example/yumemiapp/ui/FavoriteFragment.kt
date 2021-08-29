package com.example.yumemiapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yumemiapp.R
import com.example.yumemiapp.databinding.FragmentFavoriteBinding
import com.example.yumemiapp.model.data.Profile
import com.example.yumemiapp.ui.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: MainViewModel by lazy { (activity as MainActivity).mainViewModel }
    lateinit var adapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        todo

        adapter = FavoriteAdapter(requireContext())

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager(requireContext()).orientation
            )
        )

        viewModel.getFavoriteContributers()
        showProgressbar()
        viewModel.favoList.observe(viewLifecycleOwner){
            hideProgressbar()

            binding.favoriteNum.text = getString(R.string.nothing_message)

            if (it.isEmpty()) {
                binding.favoriteNum.visibility = View.VISIBLE
            } else {
                binding.favoriteNum.visibility = View.GONE
            }

            adapter.submitList(it)
            adapter.setOnDeleteClickListener(object :
                FavoriteAdapter.DeleteItem {
                override fun deleteContributer(profile: Profile) {
                    viewModel.deleteContributer(profile)
                    Toast.makeText(requireContext(), "削除", Toast.LENGTH_LONG).show()
                    viewModel.getFavoriteContributers()
                    adapter.submitList(it)
                }
            })
        }
    }

    private fun showProgressbar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressbar() {
        binding.progressBar.visibility = View.INVISIBLE
    }
}