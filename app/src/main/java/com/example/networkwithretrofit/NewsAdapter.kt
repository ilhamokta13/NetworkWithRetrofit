package com.example.networkwithretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkwithretrofit.databinding.ItemNewsBinding
import com.example.networkwithretrofit.model.ResponseDataNewsItem

class NewsAdapter(var listNews : List<ResponseDataNewsItem>):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemNewsBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.titleNews.text= listNews[position].title
        holder.binding.tglNews.text = listNews[position].createdAt
        Glide.with(holder.itemView).load(listNews[position].image).into(holder.binding.imgNews)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }
}