package com.example.news.services

import com.example.news.model.ArticleList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("everything")
    fun getArticles(@Query("apiKey") apiKey:String, @Query("q") q:String = "Cat"): Call<ArticleList>
}