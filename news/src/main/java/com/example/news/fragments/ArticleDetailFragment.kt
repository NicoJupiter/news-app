package com.example.news.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.model.Article
import kotlinx.android.synthetic.main.articles_detail_fragment.*

class ArticleDetailFragment : Fragment() {

    val article:Article by lazy {
        arguments?.getParcelable<Article>(ARGS_ARTICLE) !!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.articles_detail_fragment, container, false)
    }

    companion object {
        const val ARGS_ARTICLE = "ARGS_OPERATION"
        fun newIstance(article: Article):ArticleDetailFragment {
            return ArticleDetailFragment().apply {
               arguments = bundleOf(ARGS_ARTICLE to article)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleTitle.text = article.title
        articleText.text = article.description
        Glide.with(context!!)
            .load(article.urlToImage)
            .into(articleImage)
    }


}