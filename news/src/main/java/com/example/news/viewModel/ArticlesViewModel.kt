package com.example.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.model.Article
import com.example.news.model.ArticleList
import com.example.news.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticlesViewModel : ViewModel() {

    private val repository: ArticleRepository = ArticleRepository()
    private val _listArticles = MutableLiveData<ArticleList>()
    val listArticles: LiveData<ArticleList>
        get() = _listArticles

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getArticles()
            _listArticles.postValue(result)
        }
    }

}