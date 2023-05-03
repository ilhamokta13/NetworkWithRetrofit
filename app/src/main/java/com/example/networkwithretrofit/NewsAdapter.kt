package com.example.networkwithretrofit

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkwithretrofit.databinding.ItemNewsBinding
import com.example.networkwithretrofit.model.ResponseDataNewsItem
import com.example.networkwithretrofit.model.ResponseUpdateNews

class NewsAdapter(var listNews : List<ResponseDataNewsItem>):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var onDetailnews : ((ResponseDataNewsItem)->Unit)? = null


    class ViewHolder(var binding: ItemNewsBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        var view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder,  @SuppressLint("RecyclerView") position: Int) {
       holder.binding.titleNews.text= listNews[position].title
        holder.binding.tglNews.text = listNews[position].createdAt
        Glide.with(holder.itemView).load(listNews[position].image).into(holder.binding.imgNews)

        holder.binding.deleteCar.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val bun = Bundle()
                bun.putString("id", listNews[position].id)
            }
        })



        holder.binding.klikDetail.setOnClickListener{
            var a = Bundle()
            val inten = Intent(it.context, DetailNewsActivity::class.java)
            a.putInt("detail", listNews[position].id.toInt())
            inten.putExtras(a)
            it.context.startActivity(inten)
        }

        holder.binding.editCar.setOnClickListener {
            var edit = Intent(it.context, UpdateNewsActivity::class.java)
            edit.putExtra("update", listNews[position].id.toInt())
            it.context.startActivity(edit)
        }


    }

    override fun getItemCount(): Int {
        return listNews.size
    }
}