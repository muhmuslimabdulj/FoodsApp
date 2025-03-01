/*
* Author : M Muslim Abdul J
* */

package com.mmuslimabdulj.mynews.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mmuslimabdulj.mynews.R
import com.mmuslimabdulj.mynews.model.DataNews
import com.mmuslimabdulj.mynews.ui.NewsActivity
import com.mmuslimabdulj.mynews.util.Constans
import com.squareup.picasso.Picasso

class CarouselAdapter(private val images: List<DataNews>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_view_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val data = images[position]
        Picasso.get().load(data.linkImage).into(holder.imageView)
        holder.imageView.setOnClickListener {
            Constans.title = data.title
            Constans.image = data.linkImage
            Constans.description = data.description
            val intent = Intent(holder.imageView.context, NewsActivity::class.java)
            holder.imageView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = images.size
}