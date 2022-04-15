package com.example.retrofitproject

import MyAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {

    var myAdapter = MyAdapter()
    lateinit var txt : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recV = findViewById<RecyclerView>(R.id.my_rv)
        recV.layoutManager = LinearLayoutManager(this)
        recV.adapter = myAdapter

        getAllCharacter()
        getCharacter()

    }

    fun getAllCharacter() {

        var retrofit = Retrofit.Builder().baseUrl("https://api.disneyapi.dev")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java); //Part 2
        val call = apiInterface.getAllCharacter()
        val item = call.enqueue(object : Callback<Disney> {

            override fun onResponse(call: Call<Disney>, response: Response<Disney>) {
                Log.i("xxx", "det gick")

                val characters = response.body()

                            myAdapter.data = characters?.data
                            myAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Disney>, t: Throwable) {
                Log.i("xxx", "det gick inteeee")
            }
        })
    }


    fun getCharacter() {

        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.disneyapi.dev")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java); //Part 2
        val call = apiInterface.getCharacter()
        val item = call.enqueue(object : Callback<Character> {

            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                Log.i("xxx", "det gick")
                val character = response.body()
                Log.i("xxx", character!!.name.toString())

                txt = findViewById(R.id.my_txt)



                txt.text = character!!.name.toString() + "  " + character!!._id.toString()


            }
            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.i("xxx", "det gick inteeee")
            }
        })
    }


}

class Character {
    var name: String? = null
    var _id : Int? = null
}

class Disney {
    var data: List<Character>? = null
}

interface ApiInterface {

    @GET("/characters/48")
    fun getCharacter(): Call<Character>


    @GET("/characters")
    fun getAllCharacter(): Call<Disney>


}


