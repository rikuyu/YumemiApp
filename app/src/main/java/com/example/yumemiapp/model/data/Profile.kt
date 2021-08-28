package com.example.yumemiapp.model.data

import android.os.Parcelable

data class Profile(
    val id: Int,
    val avatar_url: String,
    val followers_url: String,
    val following_url: String,
    val name: String,
    val html_url: String,
)