package com.example.news.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(val id:Int ,val author:String, val title:String, val description:String, val urlToImage:String, var like:Boolean = false) : Parcelable