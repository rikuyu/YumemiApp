package com.example.yumemiapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yumemiapp.R
import com.example.yumemiapp.model.data.ContributersItem
import com.example.yumemiapp.model.data.Profile
import de.hdodenhof.circleimageview.CircleImageView

class HomeAdapter(private val context: Context, private val contributers: List<ContributersItem>) :
    RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    private lateinit var detailListener: OnItemClickListener

    class HomeHolder(view: View) : RecyclerView.ViewHolder(view) {
        val buttonDetail: ImageView = view.findViewById(R.id.button_detail)
        val buttonFavorite: ImageView = view.findViewById(R.id.button_favorite)
        val icon: CircleImageView = view.findViewById(R.id.contributer_icon)
        val name: TextView = view.findViewById(R.id.contributer_name)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contributer_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.name.text = contributers[position].login
        Glide.with(context).load(contributers[position].avatar_url).into(holder.icon)

        val profile = Profile(
            id = contributers[position].id,
            avatar_url = contributers[position].avatar_url,
            followers_url = contributers[position].followers_url,
            following_url = contributers[position].following_url,
            name = contributers[position].login,
            html_url = contributers[position].html_url
        )

        holder.buttonDetail.setOnClickListener {
            detailListener.onDetailClickListener(profile)
        }
    }

    override fun getItemCount(): Int {
        return contributers.size
    }

    interface OnItemClickListener {
        fun onDetailClickListener(profile: Profile)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.detailListener = listener
    }
}