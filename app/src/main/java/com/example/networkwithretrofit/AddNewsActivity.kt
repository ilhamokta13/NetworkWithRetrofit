package com.example.networkwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.networkwithretrofit.databinding.ActivityAddNewsBinding
import com.example.networkwithretrofit.viewmodel.NewsViewModel

class AddNewsActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val title = binding.title.text.toString()
            val img = binding.image.text.toString()
            val author = binding.author.text.toString()
            val desc = binding.description.text.toString()
            addNews(title,img,author,desc)
        }

    }
    fun addNews(title : String, image : String, author : String, desc : String){
        val viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        viewModel.addDataNews(title,image,author,desc)
        viewModel.postNews().observe(this, Observer {
            if (it != null){
                Toast.makeText(this, "Add Data Successfull", Toast.LENGTH_SHORT).show()

            }
        })


    }
}