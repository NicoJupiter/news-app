package com.example.news.dataSource

import com.example.news.BuildConfig
import com.example.news.model.ArticleList
import com.example.news.services.ArticleService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource {

    private val service: ArticleService
    private val apiKey:String = "c8a9ed108d2542bbafd7b4392b128c42"

    init {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder().apply {
            //Ajouter un converter pour JSON
            //Ici on utilise gson
            addConverterFactory(GsonConverterFactory.create())
            client(client)
            //Ajouter l'url de base du web service
            baseUrl("https://newsapi.org/v2/")
        }.build()
        //Créer une instance du service
        service = retrofit.create(ArticleService::class.java)
    }

    fun getRemoteArticles(): ArticleList? {
        val result = service.getArticles(apiKey).execute()
        return if(result.isSuccessful) {
           result.body()
        }else {
           null
        }
    }
}