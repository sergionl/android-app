package com.example.login.api

import com.example.login.models.loginModel
import com.example.login.models.usuario
import com.example.login.models.usuarioItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    //@FormUrlEncoded
    @POST("authenticate")
    fun login(@Body loginModel: loginModel):Call<String>
    //fun login(@Body loginModel: loginModel):String
 //fecha={fecha}&cantidad={cantidad}
    @GET("usuario?")
    fun getUsuarios(@Query("fecha") fecha: String,@Query("cantidad") cantidad:Int, @Header("Authorization") auth : String) :  Call<List<usuarioItem>>

}