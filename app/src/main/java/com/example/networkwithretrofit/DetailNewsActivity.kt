package com.example.networkwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.networkwithretrofit.databinding.ActivityDetailNewsBinding
import com.example.networkwithretrofit.model.ResponseDataNewsItem
import com.example.networkwithretrofit.viewmodel.FilmViewModel
import com.example.networkwithretrofit.viewmodel.NewsViewModel

class DetailNewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var bund = intent.extras
        var id = bund!!.getInt("detail")
        Log.d("id",id.toString())
        getDetailFilm()
    }

    fun getDetailFilm(){
        val viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.callDetailApiNews()
        viewModel.getliveDataNews().observe(this, Observer {

        })


        }

    }



