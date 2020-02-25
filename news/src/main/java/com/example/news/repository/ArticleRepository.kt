package com.example.news.repository

import com.example.news.dataSource.RemoteDataSource
import com.example.news.model.ArticleList

class ArticleRepository {

    private val online = RemoteDataSource()

    fun getArticles(): ArticleList? {
        return online.getRemoteArticles()
    }

}