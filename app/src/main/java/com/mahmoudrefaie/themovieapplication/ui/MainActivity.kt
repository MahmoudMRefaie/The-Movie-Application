package com.mahmoudrefaie.themovieapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.get
import com.mahmoudrefaie.themovieapplication.R
import com.mahmoudrefaie.themovieapplication.data.RetrofitClient
import com.mahmoudrefaie.themovieapplication.model.MovieItem
import com.mahmoudrefaie.themovieapplication.model.MovieResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    private var progressBar : ProgressBar? = null
    private var gridView:GridView ? = null
    private var movieNameAdapter : MovieNameAdapter ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api_key = "a5956e11fc7cf87dd184b612e36ca190"
        val id = 3

        progressBar = findViewById(R.id.progress_circular)
        gridView = findViewById(R.id.all_movies)

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
                    movieNameAdapter = MovieNameAdapter(this@MainActivity, allData!!)
                    gridView!!.adapter = movieNameAdapter
                    gridView!!.setOnItemClickListener(object : AdapterView.OnItemClickListener {
                        override fun onItemClick( p0: AdapterView<*>?, p1: View?, position: Int, p2: Long) {
                            var extras = Bundle()
                            extras.putString("title_key", allData!!.get(position).title)
                            extras.putInt("id_key", allData!!.get(position).id!!)
                            extras.putString("originalLanguage_key", allData!!.get(position).originalLanguage)
                            extras.putString("overview_key", allData!!.get(position).overview)
                            extras.putString("posterPath_key", allData!!.get(position).posterPath)

                            var intent = Intent(applicationContext, MovieDetails::class.java)
                            intent.putExtras(extras)
                            startActivity(intent)
                        }
                    })
                }else{
                    Log.e("OnResponse : ","else")
                    Toast.makeText(applicationContext,"ERROR",Toast.LENGTH_LONG).show()
                }
            }
        })

    }

//    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p2: Long) {
//        Log.e("onClickItem","Log at onItemClick")
//        var itemList:List<MovieResponse> ? = null
//        var item:List<MovieItem> = itemList!!.get(position).items!!
//        var detail = item.get(position).overview
//        Log.e("onClickItem","After Assign")
//        Toast.makeText(applicationContext, detail ,Toast.LENGTH_LONG).show()
//        var intent = Intent(this,MovieDetails::class.java)
//        startActivity(intent)
//    }

}