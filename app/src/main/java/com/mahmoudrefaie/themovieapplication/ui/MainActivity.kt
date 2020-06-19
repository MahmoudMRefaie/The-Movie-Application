package com.mahmoudrefaie.themovieapplication.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.mahmoudrefaie.themovieapplication.R
import com.mahmoudrefaie.themovieapplication.data.API
import com.mahmoudrefaie.themovieapplication.data.RetrofitClient
import com.mahmoudrefaie.themovieapplication.model.Movie
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , AdapterView.OnItemClickListener{

    private var arrayList:ArrayList<Movie> ? = null
    private val gridView:GridView ? = null
    private var movieNameAdapter : MovieNameAdapter ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var api_key = "a5956e11fc7cf87dd184b612e36ca190"
//        var language = "en-US"
//        var page = 1
//        var region = "EG"

        var call:Call<List<Movie>> = RetrofitClient().getMovieNameApi()!!.getMoviesName("")
        call.enqueue(object : Callback<List<Movie>>{
            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                Toast.makeText(applicationContext,"OnFailure : "+t.message,Toast.LENGTH_LONG).show()
                Log.e("onFailure : ",t.message)
            }

            override fun onResponse(
                call: Call<List<Movie>>,
                response: Response<List<Movie>>
            ) {
                if(response.isSuccessful){
                    movieNameAdapter = MovieNameAdapter(this@MainActivity,response.body()!!)
                    gridView!!.adapter = movieNameAdapter
                }else{
                    Toast.makeText(applicationContext,"ERROR",Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var item:Movie = arrayList!!.get(p2)
        Toast.makeText(applicationContext,item.title,Toast.LENGTH_LONG).show()
    }

}

