package com.mahmoudrefaie.themovieapplication

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyAsyncTaskGetMovies().execute()
    }

    internal inner class MyAsyncTaskGetMovies : AsyncTask<Void,Void, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Void?): String {

            var api_key = "a5956e11fc7cf87dd184b612e36ca190"
            var movie_id = 1
            while(true) {
                movie_id.toString()
                val myUrl =
                    "https://api.themoviedb.org/3/movie/$movie_id?api_key=$api_key&language=en-US"
                val url = URL(myUrl)
                val httpClient = url.openConnection() as HttpURLConnection
                if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
                    try {
                        val stream = BufferedInputStream(httpClient.inputStream)
                        val data: String = readStream(inputStream = stream)
                        return data
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        httpClient.disconnect()
                    }
                } else {
                    println("ERROR ${httpClient.responseCode}")
                }
                movie_id++
                return ""
            }
        }
        fun readStream(inputStream: BufferedInputStream): String {
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            bufferedReader.forEachLine { stringBuilder.append(it) }
            return stringBuilder.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                var root = JSONObject(result)
                var title = root.getString("title")
                text.text = title
            }catch(ex : Exception){
                ex.printStackTrace()
            }
        }

    }
}
