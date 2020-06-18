package com.mahmoudrefaie.themovieapplication.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.mahmoudrefaie.themovieapplication.R
import com.mahmoudrefaie.themovieapplication.model.Movie

class MovieNameAdapter(val context: Context, val grid : ArrayList<Movie>) : BaseAdapter() {


    override fun getView(p0: Int, p1: View?, parent: ViewGroup?): View {
        val view:View = LayoutInflater.from(context).inflate(R.layout.movie_name_item,parent,false)

        val movie_name = view.findViewById<TextView>(R.id.movie_name)
        /*val overview = view.findViewById<TextView>(R.id.overview)
        val original_language = view.findViewById<TextView>(R.id.original_language)
        val popularity = view.findViewById<TextView>(R.id.popularity)
        val release_date = view.findViewById<TextView>(R.id.release_date)*/

        var listItem : Movie = grid.get(p0)

        movie_name.text = grid.get(p0).title
        /*overview.text = grid[p0].overview
        original_language.text = grid[p0].original_language
        popularity.text = grid[p0].popularity
        release_date.text = grid[p0].release_date*/



        return view
    }

    override fun getItem(p0: Int): Any {
        return grid.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()

    }

    override fun getCount(): Int {
        return grid.size
    }

}