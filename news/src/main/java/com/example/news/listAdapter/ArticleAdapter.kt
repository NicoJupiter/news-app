package com.example.news.listAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.model.Article


class ArticleAdapter (val handler : (article : Article) -> Unit): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private val dataset: MutableList<Article> = mutableListOf()

    inner class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {

        fun bind(item: Article) {
            val txtTitle = root.findViewById<TextView>(R.id.article_title)
            //val txtDesc = root.findViewById<TextView>(R.id.article_description)
            val imgArticle = root.findViewById<ImageView>(R.id.article_image)
            val likeIcon = root.findViewById<ImageView>(R.id.article_like)
            txtTitle.text = item.title
            //txtDesc.text = item.description

            Glide.with(root.context!!)
                .load(item.urlToImage)
                .into(imgArticle)

            likeIcon.setOnClickListener{
                println(item.title)

            }

            root.setOnClickListener {
                handler(item)
            }

        }
    }

    fun updateData(list: List<Article>) {
        dataset.clear()
        dataset.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size




}