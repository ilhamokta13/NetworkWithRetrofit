package com.example.networkwithretrofit.google

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.networkwithretrofit.MainActivity
import com.example.networkwithretrofit.R
import com.google.firebase.auth.FirebaseAuth

class GoogleHomeActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_home)

        auth = FirebaseAuth.getInstance()

        val email = intent.getStringExtra("email")
        val displayName = intent.getStringExtra("name")

        // menampilkan name dan username dari hasil login sebelumnya
        findViewById<TextView>(R.id.textView).text = email + "\n" + displayName

        findViewById<Button>(R.id.signOutBtn).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this , GoogleMainActivity::class.java))
        }

    }
}