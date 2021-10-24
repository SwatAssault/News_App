package com.rkhasanov.newsApp

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val button = findViewById<Button>(R.id.button)
        val view = findViewById<TextView>(R.id.textView)
        button.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "https://newsapi.org/v2/everything?qInTitle=Microsoft"

            val params = JSONObject()
            params.put("X-Api-Key", "e1b919c8ae3d48658095b4fed9091816")

            val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, params,
                { response ->
                    view.text = "Response: %s".format(response.toString())
                },
                { error ->
                    view.text = error.message
                }) {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headers = HashMap<String, String>()
                        headers["Authorization"] = "e1b919c8ae3d48658095b4fed9091816"
                        return headers
                    }
                }
            queue.add(jsonObjectRequest)

//            val url = URL("https://newsapi.org/v2/everything?qInTitle=Microsoft&apiKey=e1b919c8ae3d48658095b4fed9091816")
//
//            with(url.openConnection() as HttpURLConnection) {
//                requestMethod = "GET"
//
//                inputStream.bufferedReader().use {
//                    it.lines().forEach { line ->
//                        println(line)
//                    }
//                }
//            }

        }
    }
}