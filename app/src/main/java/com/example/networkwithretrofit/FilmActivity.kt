package com.example.networkwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkwithretrofit.databinding.ActivityFilmBinding
import com.example.networkwithretrofit.model.ResponseDataFilm
import com.example.networkwithretrofit.model.ResponseDataFilmItem
import com.example.networkwithretrofit.model.ResponseDataNewsItem
import com.example.networkwithretrofit.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmActivity : AppCompatActivity() {
    lateinit var binding: ActivityFilmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFilm()
    }

    fun getDataFilm(){
        RetrofitClient.instance.getAllFilm().enqueue(object : Callback<List<ResponseDataFilmItem>>{
            override fun onResponse(
                call: Call<List<ResponseDataFilmItem>>,
                response: Response<List<ResponseDataFilmItem>>
            ) {
                if (response.isSuccessful){
                    binding.rvFilm.layoutManager = LinearLayoutManager(this@FilmActivity, LinearLayoutManager.VERTICAL,false)
                    binding.rvFilm.adapter = FilmAdapter(response.body()!!)
                }else{
                    Toast.makeText(this@FilmActivity,"Failded load data", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<List<ResponseDataFilmItem>>, t: Throwable) {
                Toast.makeText(this@FilmActivity, "", Toast.LENGTH_SHORT).show()
            }

        })
    }


}







