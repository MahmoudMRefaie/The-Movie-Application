package com.mahmoudrefaie.themovieapplication.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mahmoudrefaie.themovieapplication.R

class MovieDetails : AppCompatActivity() {

    var movieName: TextView ? = null
    var movieId: TextView ? = null
    var movieLanguage: TextView ? = null
    var movieOverview: TextView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_details)

        val intent = getIntent()
        val extras = intent.extras
        var extraMovieName = extras?.getString("title_key")
        var extraMovieId = extras?.getInt("id_key")
        var extraMovieLanguage = extras?.getString("originalLanguage_key")
        var extraMovieOverview = extras?.getString("overview_key")

        movieName = findViewById(R.id.movieTitle)
        movieId = findViewById(R.id.movieId)
        movieLanguage = findViewById(R.id.movieLanguage)
        movieOverview = findViewById(R.id.movieOverview)

        movieName!!.text = extraMovieName
        movieId!!.text = extraMovieId.toString()
        movieLanguage!!.text = extraMovieLanguage
        movieOverview!!.text = extraMovieOverview

    }
}