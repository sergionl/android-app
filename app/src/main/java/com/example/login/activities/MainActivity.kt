package com.example.login.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R
import com.example.login.activities.adapters.userAdapter
import com.example.login.api.RetrofitClient
import com.example.login.models.loginModel
import com.example.login.models.usuarioItem
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

     fun enviar(view: View) {

        val emailText = findViewById<TextView>(R.id.emailText)
        val passwordText = findViewById<TextView>(R.id.TextPassword)
        val email:String = emailText.text.toString()
        val password:String = passwordText.text.toString()

         if (email.isEmpty()){
             Log.d("error","error")
             emailText.error = "Email required"
             return
         }
         if (password.isEmpty()){
             Log.d("error","error2")
             passwordText.error="Password required"
             return
         }

        //Log.d("email",email)
        //Log.d("password",password)

         val loginModel : loginModel = loginModel(email,password)

         var tokenData = RetrofitClient.api.login(loginModel)

         Log.d("message","token obtain")


         //val intent = Intent(this,userRecycle::class.java)
         val intent = Intent(this,inputQueryData::class.java)
         tokenData.enqueue(object : Callback<String?> {
             override fun onResponse(call: Call<String?>, response: Response<String?>) {
                 val tokenBody = response.body()!!
                 Log.d("data",tokenBody.toString())

                 var bearer = "Bearer "+ tokenBody

                 //Log.d("complete token",bearer)
                 intent.putExtra("token",bearer)

                // Log.d("msg","Change screen")

                 startActivity(intent)
             }

             override fun onFailure(call: Call<String?>, t: Throwable) {
                 Log.d("Error Token","onFailure: " + t.message)
             }
         })


    }
}