package com.mahmoudrefaie.themovieapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.mahmoudrefaie.themovieapplication.R
import com.mahmoudrefaie.themovieapplication.data.RetrofitClient
import com.mahmoudrefaie.themovieapplication.model.MovieItem
import com.mahmoudrefaie.themovieapplication.model.MovieResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() , AdapterView.OnItemClickListener{

    private var arrayList:ArrayList<MovieResponse> ? = null
    private val gridView:GridView ? = null
    private var movieNameAdapter : MovieNameAdapter ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var api_key = "a5956e11fc7cf87dd184b612e36ca190"
//        var language = "en-US"
//        var page = 1
//        var region = "EG"

        var call:Call<MovieResponse> = RetrofitClient().getMovieNameApi()!!.getMoviesName(api_key)
        call.enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(applicationContext,"OnFailure : "+t.message,Toast.LENGTH_LONG).show()
                Log.e("onFailure : ",t.message)
            }

            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                if(response.isSuccessful){
                    //Log.e("OnResponse : ",response.body()?.items?.get(0)?.title)
                    var allData : List<MovieItem> = response.body()!!.items!!
                    Log.e("OnResponse : ", allData!!.toString())
                    movieNameAdapter = MovieNameAdapter(this@MainActivity, allData!!)
                    gridView!!.adapter = movieNameAdapter
                }else{
                    Log.e("OnResponse : ","else")
                    Toast.makeText(applicationContext,"ERROR",Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //var item:MovieResponse = arrayList!!.get(p2)
        //Toast.makeText(applicationContext,item.desc,Toast.LENGTH_LONG).show()
    }

}