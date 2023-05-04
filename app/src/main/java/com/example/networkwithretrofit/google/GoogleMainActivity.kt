package com.example.networkwithretrofit.google

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.networkwithretrofit.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class GoogleMainActivity : AppCompatActivity() {

    //Inisialisasi vaariabel auth ke authentikasi firebase
    private lateinit var auth : FirebaseAuth
    //Inisialisasi vaariabel googleSignInClient ke authentikasi firebase menggunakan google
    private lateinit var googleSignInClient : GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_main)
        //untuk mendapatkan objek autentikasi ke firebase
        auth = FirebaseAuth.getInstance()

        //Memanggil Fungsi Sign In Menggunakan Google
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //default web client didapatkan dari google services json
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Mendeklarasikan variabel googleSignInClient yang nantinya diisi oleh variabel gso yg
        //sebelumnya sudah dipanggil fungsinya
        googleSignInClient = GoogleSignIn.getClient(this , gso)

        // Memanggil fungsi sign in google pada  button
        findViewById<Button>(R.id.gSignInBtn).setOnClickListener {
            signInGoogle()
        }
    }

    //kodingan ini digunakan untuk memulai proses sign-in dengan Google
    // dengan menggunakan objek googleSignInClient. Objek launcher akan digunakan untuk
    // memulai aktivitas sign-in dan menangani hasil autentikasi ketika aktivitas tersebut selesai.
    private fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    //kodingan ini digunakan untuk mendaftarkan sebuah callback yang akan dijalankan ketika
    // aktivitas sign-in Google selesai. Callback ini akan menangani hasil autentikasi dari
    // aktivitas tersebut dan memanggil fungsi handleResults() untuk menangani hasil
    // autentikasi yang berhasil.
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == Activity.RESULT_OK){

            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }


    //kodingan ini digunakan untuk menangani hasil autentikasi dari sign-in Google dan

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null){
                updateUI(account)
            }
        }else{
            Toast.makeText(this, task.exception.toString() , Toast.LENGTH_SHORT).show()
        }
    }

    // memperbarui UI aplikasi dengan informasi akun Google yang berhasil terautentikasi.
    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                val intent : Intent = Intent(this , GoogleHomeActivity::class.java)
                intent.putExtra("email" , account.email)
                intent.putExtra("name" , account.displayName)
                startActivity(intent)
            }else{
                Toast.makeText(this, it.exception.toString() , Toast.LENGTH_SHORT).show()

            }
        }

    }
}