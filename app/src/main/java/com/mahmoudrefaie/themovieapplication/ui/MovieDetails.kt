package com.mahmoudrefaie.themovieapplication.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mahmoudrefaie.themovieapplication.R

class MovieDetails : AppCompatActivity() {

    private var movieName: TextView ? = null
    private var movieId: TextView ? = null
    private var movieLanguage: TextView ? = null
    private var movieOverview: TextView ? = null
    private var movieImage: ImageView ? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        val baseImageUrl = "http://image.tmdb.org/t/p/original/"

        val intent = getIntent()
        val extras = intent.extras
        var extraMovieName = extras?.getString("title_key")
        var extraMovieId = extras?.getInt("id_key")
        var extraMovieLanguage = extras?.getString("originalLanguage_key")
        var extraMovieOverview = extras?.getString("overview_key")
        var extraMovieImage = extras?.getString("posterPath_key")

        val imageUrl = "http://image.tmdb.org/t/p/original/$extraMovieImage"    //original is the image size and there "w92", "w154", "w185", "w342", "w500", "w780"

        movieName = findViewById(R.id.movieTitle)
        movieId = findViewById(R.id.movieId)
        movieLanguage = findViewById(R.id.movieLanguage)
        movieOverview = findViewById(R.id.movieOverview)
        movieImage = findViewById(R.id.movieImage)

        movieName!!.text = extraMovieName
        movieId!!.text = extraMovieId.toString()
        movieLanguage!!.text = extraMovieLanguage
        movieOverview!!.text = extraMovieOverview

        progressBar = findViewById(R.id.progress)

        Glide.with(this)
            .load(imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    progressBar!!.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    progressBar!!.visibility = View.GONE
                    return false
                }
            })
            .into(movieImage!!)

    }
}