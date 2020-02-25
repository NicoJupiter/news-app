package com.example.news.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.listAdapter.ArticleAdapter
import com.example.news.model.Article
import com.example.news.viewModel.ArticlesViewModel
import kotlinx.android.synthetic.main.articles_list_fragment.*

class ArticlesFragment : Fragment() {

    private val adapter = ArticleAdapter(handler = {
        switchMoreDetail(it)
    })

    lateinit var articlesViewModel: ArticlesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.articles_list_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articlesViewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)
        articlesViewModel.loadData()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        articlesViewModel.listArticles.observe(viewLifecycleOwner, Observer {

            adapter.updateData(it.articles)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //définir l'orientation des élements (vertical)
        list_articles.layoutManager = LinearLayoutManager(context)
        //associer l'adapter à la recyclerview
        list_articles.adapter = adapter


    }

    fun switchMoreDetail(article:Article) {
        val articleDetailFragment = ArticleDetailFragment.newIstance(article)

        activity!!.supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, articleDetailFragment)
            addToBackStack(null)
        }.commit()

    }

}