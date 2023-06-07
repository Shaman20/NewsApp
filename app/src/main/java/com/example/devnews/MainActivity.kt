package com.example.devnews

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(){

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

    }

    private fun loadData() {
        val call = RetrofitClient.api.getEverything()
        call.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
//               Log.i(TAG,"Call is $call")
               Log.i(TAG, "Response is ${response.body()}")
                if (response.body()?.status == "ok") {
                    val list: List<Article> = response.
                    body()!!.articles
                    if (list.isNotEmpty()) {

                        recyclerView = findViewById(R.id.recyclerView)
                        recyclerView.adapter = NewsAdapter(list, this@MainActivity)
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.i(TAG, "Error is ${t.message}")
            }

        })
    }
}