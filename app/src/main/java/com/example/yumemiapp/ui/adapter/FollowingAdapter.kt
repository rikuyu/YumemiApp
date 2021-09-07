package com.example.yumemiapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yumemiapp.R
import com.example.yumemiapp.model.data.ContributorsItem
import de.hdodenhof.circleimageview.CircleImageView

class FollowingAdapter(
    private val context: Context,
    private val followings: List<ContributorsItem>
) : RecyclerView.Adapter<FollowingAdapter.FollowingHolder>() {

    class FollowingHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: CircleImageView = view.findViewById(R.id.contributer_icon)
        val name: TextView = view.findViewById(R.id.contributer_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingHolder {
        return FollowingAdapter.FollowingHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.following_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FollowingHolder, position: Int) {
        holder.name.text = followings[position].login
        Glide.with(context).load(followings[position].avatar_url).into(holder.icon)
    }

    override fun getItemCount(): Int {
        return followings.size
    }
}