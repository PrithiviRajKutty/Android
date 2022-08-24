package com.example.recycler_view_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>?=null
    val name = arrayListOf<String>()

    var image = arrayListOf<String>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        run("https://www.reddit.com/r/all.json")
        Thread.sleep(3000)



        layoutManager=LinearLayoutManager(this)
        recyclerview.layoutManager=layoutManager
        adapter=RecyclerAdapter(MockList.getModel(image,name) as ArrayList<Model>)

        recyclerview.adapter=adapter


    }

fun run(url: String){
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
                            val gif = result3.getString("thumbnail")
                            name.add(final_value)
                            image.add(gif)

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