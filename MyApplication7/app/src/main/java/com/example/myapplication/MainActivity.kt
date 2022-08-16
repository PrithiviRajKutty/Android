package com.example.myapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    private val name = arrayListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        run("https://www.reddit.com/r/all.json")
        Thread.sleep(3000)
        list_view.adapter = ListAdapter(this, name)
        list_view.setOnItemClickListener { parent, view, position, id ->

            if (position == 0 ){

                Toast.makeText(this,"title",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                val title1= response.body()?.string()
                try {
                    val jsonObject = JSONObject(title1)
                    val results: JSONObject = jsonObject.getJSONObject("data")
                    for (i in 0 until results.length()) {
                        val result1: JSONArray = results.getJSONArray("children")
                        for (j in 0 until result1.length()) {
                            val result2: JSONObject = result1.getJSONObject(j)
                            for (k in 0 until 1) {
                                val result3: JSONObject = result2.getJSONObject("data")
                                val final_value = result3.getString("title")
                                name.add(final_value)
                            }
                        }
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                    println(e)
                }

                }


        })
    }
}
