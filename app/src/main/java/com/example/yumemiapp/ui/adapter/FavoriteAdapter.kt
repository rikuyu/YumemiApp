package com.example.yumemiapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yumemiapp.databinding.FavoriteItemBinding
import com.example.yumemiapp.model.data.Profile

class FavoriteAdapter(private val context: Context): ListAdapter<Profile, FavoriteAdapter.FavoriteHolder>(FavoriteComparator()) {

    private lateinit var deleteBtnListener: DeleteItem

    inner class FavoriteHolder(private val binding: FavoriteItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(profile: Profile){
            binding.apply {
                favoriteContributerName.text = profile.name
                Glide.with(context).load(profile.avatar_url).into(favoriteContributerIcon)
                buttonDelete.setOnClickListener {
                    deleteBtnListener.deleteContributer(profile)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteHolder {
        val binding =
            FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface DeleteItem {
        fun deleteContributer(profile: Profile)
    }

    fun setOnDeleteClickListener(listener: DeleteItem) {
        this.deleteBtnListener = listener
    }

    class FavoriteComparator : DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(oldItem: Profile, newItem: Profile) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Profile, newItem: Profile) =
            oldItem == newItem
    }
}