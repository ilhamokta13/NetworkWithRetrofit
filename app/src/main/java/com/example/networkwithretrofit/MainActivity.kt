package com.example.networkwithretrofit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkwithretrofit.databinding.ActivityMainBinding
import com.example.networkwithretrofit.model.ResponseDataNewsItem
import com.example.networkwithretrofit.network.RetrofitClient
import com.example.networkwithretrofit.viewmodel.NewsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var newsAdapter : NewsAdapter
//    var onDetail : ((ResponseDataNewsItem)->Unit)?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val move = Intent(this, AddNewsActivity::class.java)
            startActivity(move)

        }
        setVmtoAdapter()
    }

        fun setVmtoAdapter(){
            val viewModel =ViewModelProvider(this).get(NewsViewModel::class.java)
            viewModel.callApiNews()
            viewModel.getliveDataNews().observe(this, Observer {
                newsAdapter = NewsAdapter(it)
                if ( it != null){
                    binding.rvNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    binding.rvNews.adapter = NewsAdapter(it)

                }

                    newsAdapter.onDetailnews ={
                        var getData = it
                        var inten = Intent(this, DetailNewsActivity::class.java)
                        inten.putExtra("Detail",getData)
                        startActivity(inten)
                    }


            })
        }



    }









