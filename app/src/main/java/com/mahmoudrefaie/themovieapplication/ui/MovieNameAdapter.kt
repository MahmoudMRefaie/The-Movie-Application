package com.mahmoudrefaie.themovieapplication.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudrefaie.themovieapplication.R
import com.mahmoudrefaie.themovieapplication.model.MovieItem
import kotlinx.android.synthetic.main.movie_name_item.view.*
import org.w3c.dom.Text

class MovieNameAdapter(val movies : List<MovieItem>) : RecyclerView.Adapter<MovieNameAdapter.MovieHolder>() /*(val context: Context, val grid : List<MovieItem>) : BaseAdapter()*/ {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_name_item,parent,false)
        return MovieHolder(view)
    }

    override fun getItemCount() : Int = movies.size

    override fun onBindViewHolder(holder: MovieNameAdapter.MovieHolder, position: Int) {
        val context=holder.movieTitle?.context
        val movie = movies[position]
        holder.bindMode(movie)
        holder.itemView.setOnClickListener {
            var extras = Bundle()
            extras.putString("posterPath_key", movies!![position].posterPath)
            extras.putString("title_key", movies!![position].title)
            extras.putString("overview_key", movies!![position].overview)
            extras.putString("releaseDate_key", movies!![position].releaseDate)
            extras.putString("originalLanguage_key", movies!![position].originalLanguage)
            extras.putDouble("vote_key", movies!![position].vote!!)
            extras.putBoolean("adult_key", movies!![position].adult!!)

            var intent = Intent(context, MovieDetails::class.java)
            intent.putExtras(extras)
            context?.startActivity(intent)
        }
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val movieTitle : TextView? = itemView.findViewById(R.id.movie_name)

        fun bindMode(movieDetail: MovieItem){
            movieTitle?.text = movieDetail.title
        }
    }

}