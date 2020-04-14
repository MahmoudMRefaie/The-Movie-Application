package com.mahmoudrefaie.themovieapplication


import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mahmoudrefaie.themovieapplication.JSON.MyAdapter
import com.mahmoudrefaie.themovieapplication.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {


    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var api_key = "a5956e11fc7cf87dd184b612e36ca190"
        var movie_id = "3"
        val myUrl =
            "https://api.themoviedb.org/3/movie/$movie_id?api_key=$api_key&language=en-US"

        AsyncTaskHandler().execute(myUrl)
    }

    inner class AsyncTaskHandler : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@MainActivity)
            progressDialog.setMessage("Please Wait ...")
            progressDialog.setCancelable(false)
            progressDialog.show()

        }

        override fun doInBackground(vararg url: String?): String {
            /*var response = URL(url[0]).readText()
            var gson = Gson()

            val myData = gson.fromJson(response,Array<Response>::class.java).toString()

            return myData*/
            var res: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection
            try {
                connection.connect()
                res = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
            } finally {
                connection.disconnect()
            }
            return res
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            if (progressDialog.isShowing()) {
                progressDialog.dismiss()
                jsonResult(result)
            }
        }

        private fun jsonResult(jsonString: String?) {
            val jsonArray = JSONArray(jsonString)
            val myGrid = ArrayList<Movie>()
            var i = 0
            while (i < jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                myGrid.add(
                    Movie(
                        jsonObject.getString("title"),
                        jsonObject.getString("overview"),
                        jsonObject.getString("original_language"),
                        jsonObject.getString("popularity"),
                        jsonObject.getString("release_date")
                    )
                )
                i++
            }
            val adapter = MyAdapter(this@MainActivity, myGrid)
            all_movies.adapter = adapter
        }
    }
}
