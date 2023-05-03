package com.example.networkwithretrofit.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkwithretrofit.NewsAdapter
import com.example.networkwithretrofit.model.DataNews
import com.example.networkwithretrofit.model.ResponseAddNews
import com.example.networkwithretrofit.model.ResponseDataNewsItem
import com.example.networkwithretrofit.model.ResponseUpdateNews
import com.example.networkwithretrofit.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    var liveDataNews : MutableLiveData<List<ResponseDataNewsItem>> = MutableLiveData()
    var postDataNews : MutableLiveData<ResponseAddNews> = MutableLiveData()
    var updDataNews : MutableLiveData<List<ResponseUpdateNews>> = MutableLiveData()

    fun getliveDataNews() :MutableLiveData<List<ResponseDataNewsItem>>{
        return  liveDataNews
    }


    fun postNews ():MutableLiveData<ResponseAddNews>{
        return postDataNews
    }

    fun putNews():MutableLiveData<List<ResponseUpdateNews>>{
        return updDataNews
    }

    fun callApiNews() {
        RetrofitClient.instance.getAllNews().enqueue(object : Callback<List<ResponseDataNewsItem>> {
            override fun onResponse(
                call: Call<List<ResponseDataNewsItem>>,
                response: Response<List<ResponseDataNewsItem>>
            ) {
                if (response.isSuccessful) {
                    liveDataNews.postValue(response.body())
                } else {
                    liveDataNews.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
                liveDataNews.postValue(null)

            }

        })
    }

        fun addDataNews(title : String, image : String, author : String, desc : String){
            RetrofitClient.instance.postDataNews(DataNews(title,image,author,desc)).enqueue(object : Callback<ResponseAddNews>{
                override fun onResponse(
                    call: Call<ResponseAddNews>,
                    response: Response<ResponseAddNews>
                ) {
                    if (response.isSuccessful){

                        postDataNews.postValue(response.body())

                    }else{
                        postDataNews.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseAddNews>, t: Throwable) {
                    postDataNews.postValue(null)
                }

            })
        }
    fun calUpdDataNews(id : Int, title: String, image : String, author : String, desc : String) {
        RetrofitClient.instance.updateDataNews(id, DataNews(title, image, desc, author))
            .enqueue(object : Callback<List<ResponseUpdateNews>> {
                override fun onResponse(
                    call: Call<List<ResponseUpdateNews>>,
                    response: Response<List<ResponseUpdateNews>>
                ) {

                    if(response.isSuccessful){
                        updDataNews.postValue(response.body())
                    }else{
                        updDataNews.postValue(null)
                    }

                }

                override fun onFailure(call: Call<List<ResponseUpdateNews>>, t: Throwable) {
                    updDataNews.postValue(null)

                }

            })
    }

    fun callDetailApiNews(){
        RetrofitClient.instance.getAllNews().enqueue(object : Callback<List<ResponseDataNewsItem>> {
            override fun onResponse(
                call: Call<List<ResponseDataNewsItem>>,
                response: Response<List<ResponseDataNewsItem>>
            ) {
                if (response.isSuccessful){
                    liveDataNews.postValue(response.body())
                }else{
                    liveDataNews.postValue(null)
                }

            }

            override fun onFailure(call: Call<List<ResponseDataNewsItem>>, t: Throwable) {
                liveDataNews.postValue(null)

            }


        })

    }



}