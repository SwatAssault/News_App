package com.rkhasanov.newsApp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import okhttp3.*
import okhttp3.Headers.Companion.toHeaders
import org.json.JSONObject
import pojo.RequestResult
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val view = findViewById<TextView>(R.id.textView)
        button.setOnClickListener {
            run()
        }
    }

    private fun run() {
        val url = "https://newsapi.org/v2/everything?qInTitle=Microsoft"
        val client = OkHttpClient()

        val map = mapOf("X-Api-Key" to "e1b919c8ae3d48658095b4fed9091816")
        val headers: Headers = map.toHeaders()

        val request = Request.Builder().url(url).headers(headers).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (response.isSuccessful) {
                        //var jsonResponse = JSONObject(response.body!!.string())
                        var result = Gson().fromJson(response.body!!.string(), RequestResult::class.java)
                        print("324")
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }

}