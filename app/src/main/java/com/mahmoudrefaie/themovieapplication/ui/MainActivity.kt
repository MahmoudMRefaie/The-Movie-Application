package com.mahmoudrefaie.themovieapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudrefaie.themovieapplication.R
import com.mahmoudrefaie.themovieapplication.data.RetrofitClient
import com.mahmoudrefaie.themovieapplication.model.MovieItem
import com.mahmoudrefaie.themovieapplication.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    /*
        Constraints Layout -> done
        RecyclerView -> done
        Data Manager and Interface
        Strings at values
     */

    private var progressBar : ProgressBar? = null
    private var recycler:RecyclerView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api_key = "a5956e11fc7cf87dd184b612e36ca190"
        val id = 3

        progressBar = findViewById(R.id.progress_circular)
        recycler = findViewById(R.id.recycler)

        var call:Call<MovieResponse> = RetrofitClient().getMovieNameApi()!!.getMoviesName(id,api_key)
        call.enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"OnFailure : "+t.message,Toast.LENGTH_LONG).show()
                Log.e("onFailure : ",t.message)
                progressBar!!.setVisibility(View.INVISIBLE)
            }

            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if(response.isSuccessful){
                    progressBar!!.setVisibility(View.INVISIBLE)
                    var allData : List<MovieItem> = response.body()!!.items!!
                    Log.e("OnResponse : ", allData!!.toString())
                    showData(allData!!)
                }else{
                    Log.e("OnResponse : ","else")
                    Toast.makeText(applicationContext,"ERROR",Toast.LENGTH_LONG).show()
                }
            }
        })

    }
    // Note: activity movie_name_item height should be wrab_content
    private fun showData(movies : List<MovieItem>){
        recycler!!.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MovieNameAdapter(movies)
        }
    }
}