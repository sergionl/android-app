package com.example.login.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.activities.adapters.userAdapter
import com.example.login.api.RetrofitClient
import com.example.login.models.usuarioItem
import retrofit2.Call
import retrofit2.Response
import java.util.*


class userRecycle : AppCompatActivity() {

    lateinit var myAdapter: userAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_recycle)

        val token = intent.getStringExtra("token")
        val fecha = intent.getStringExtra("fecha")
        val cantidad = intent.getIntExtra("cantidad",1)

        val view= findViewById<RecyclerView>(R.id.recyclerview_users)
        view.setHasFixedSize(true)


        linearLayoutManager = LinearLayoutManager( this)
        view.layoutManager=linearLayoutManager
        //val cant = cantidad.toInt()

        Log.d("fecha",fecha.toString())
        Log.d("cantidad",cantidad.toString())

        //"2020-03-05 16:37:52.614"

        //val userData = RetrofitClient.api.getUsuarios(fecha.toString(),cantidad,token.toString())
        val userData = RetrofitClient.api.getUsuarios(fecha.toString(),100,token.toString())
        Log.d("msg","data obtained")

        val usuarioTemp = usuarioItem(
            id = 0,
            clogin = "",
            cnombre = "",
            bactivo = true,
            dfecreg = "",
            dfecmod = "",
            ccodigosap = null,
            idusureg = null,
            idusumod = 0,
            idempleado = 0

        )

        val tmp = listOf<usuarioItem>(usuarioTemp)
        Log.d("tmp",tmp.toString())
        

        var myAdapterTmp = userAdapter(baseContext,tmp)
        view.adapter=myAdapterTmp
        //myAdapter.notifyDataSetChanged()


        userData.enqueue(object : retrofit2.Callback<List<usuarioItem>?> {
            override fun onResponse(
                call: Call<List<usuarioItem>?>,
                response: Response<List<usuarioItem>?>
            ) {
                try {
                    val responseBody = response.body()!!
                    Log.d("data",responseBody.toString())
                    myAdapter = userAdapter(baseContext,responseBody)
                    myAdapter.notifyDataSetChanged()
                    view.adapter=myAdapter
                }catch (e: java.lang.NullPointerException){
                    Log.d("error data","empty answer")
                }





            }

            override fun onFailure(call: Call<List<usuarioItem>?>, t: Throwable) {
                Log.d("Error","onFailure: " + t.message)
            }
        })


    }
}

class inputQueryData : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {


    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = ""
    var savedMonth = ""
    var savedYear = ""
    var savedHour = ""
    var savedMinute = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_query_data)

        val token = intent.getStringExtra("token")

        pickDate()


        val btn = findViewById<Button>(R.id.siguiente)
        val txt_cant = findViewById<EditText>(R.id.cantidadText)

        btn.setOnClickListener{
            if(savedDay.length == 1){
                savedDay = "0$savedDay"
            }
            if (savedMonth.length == 1){
                savedMonth = "0$savedMonth"
            }
            var fecha = "$savedYear-$savedMonth-$savedDay $savedHour:$savedMinute:00.000"
            var cantidad = txt_cant.text.toString()
            var cant = cantidad.toInt()
            val intent = Intent(this,userRecycle::class.java)
            intent.putExtra("token",token)
            intent.putExtra("fecha",fecha)
            intent.putExtra("cantidad",cant)
            startActivity(intent)
        }

    }

    private fun getDateTimeCalendar(){
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickDate(){
        val btn = findViewById<Button>(R.id.btn_timePicker)

        btn.setOnClickListener{
            getDateTimeCalendar()
            DatePickerDialog(this, this, year, month, day).show()
        }








    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth.toString()
        savedMonth = month.toString()
        savedYear = year.toString()

        getDateTimeCalendar()
        TimePickerDialog(this,this,hour,minute,true).show()
    }

    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay.toString()
        savedMinute = minute.toString()

        val txtv = findViewById<TextView>(R.id.tv_textTime)

        txtv.text = "$savedYear-$savedMonth-$savedDay \n Hour: $savedHour : $savedMinute "
    }
}