/*
* Author : M Muslim Abdul J
* */

package com.mmuslimabdulj.mynews.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mmuslimabdulj.mynews.R
import com.mmuslimabdulj.mynews.model.DataNews
import com.mmuslimabdulj.mynews.ui.NewsActivity
import com.mmuslimabdulj.mynews.util.Constans
import com.squareup.picasso.Picasso

class NewsAdapter(private val newsList: List<DataNews>, private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        holder.txtTitle.text = news.title
        Picasso.get().load(news.linkImage).into(holder.imgPost)

        holder.layClick.setOnClickListener {
            Constans.title = news.title
            Constans.image = news.linkImage
            Constans.description = news.description
            val intent = Intent(context, NewsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = newsList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.tv_news)
        val imgPost: ImageView = itemView.findViewById(R.id.img_post)
        val layClick: CardView = itemView.findViewById(R.id.layclick)
    }
}