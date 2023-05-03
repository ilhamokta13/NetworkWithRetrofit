package com.example.networkwithretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkwithretrofit.databinding.ActivityMovieListBinding
import com.example.networkwithretrofit.model.ResponseDataNewsItem
import com.example.networkwithretrofit.network.RetrofitClient
import com.example.networkwithretrofit.viewmodel.NewsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieActivityList : AppCompatActivity() {
    lateinit var binding: ActivityMovieListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataNews()
    }

        fun getDataNews() {
            RetrofitClient.instance.getAllNews().enqueue(object :
                Callback<List<ResponseDataNewsItem>> {
                override fun onResponse(
                    call: Call<List<ResponseDataNewsItem>>,
                    response: Response<List<ResponseDataNewsItem>>
                ) {
                    if (response.isSuccessful) {
//                    binding.rvNews.layoutManager =
//                        LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
//                    binding.rvNews.adapter = NewsAdapter(response.body()!!)

                       showDataNews()

                    } else {
                        Toast.makeText(this@MovieActivityList, "Failded load data", Toast.LENGTH_SHORT)
                            .show()
                    }

                }

                override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
                    Toast.makeText(this@MovieActivityList, "", Toast.LENGTH_SHORT).show()
                }

            })
        }

        fun showDataNews() {
            val viewModelNews = ViewModelProvider(this).get(NewsViewModel::class.java)
            viewModelNews.callApiNews()
            viewModelNews.liveDataNews.observe(this, Observer {
                if (it != null) {
                    binding.rvNews.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    binding.rvNews.adapter = NewsAdapter(it)
                }



            })
        }






    }
