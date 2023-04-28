package com.example.networkwithretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkwithretrofit.databinding.ItemFilmBinding
import com.example.networkwithretrofit.model.ResponseDataFilmItem

class FilmAdapter(var listFilm : List<ResponseDataFilmItem>):RecyclerView.Adapter<FilmAdapter.ViewHolder>() {
    class ViewHolder (var binding: ItemFilmBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.titleNews.text = listFilm[position].name
        holder.binding.tglNews.text = listFilm[position].date
        holder.binding.directorNews.text = listFilm[position].director
        holder.binding.descriptionNews.text=listFilm[position].description
        Glide.with(holder.itemView).load(listFilm[position].image).into(holder.binding.imgFilm)
    }

    override fun getItemCount(): Int {
       return listFilm.size
    }
}