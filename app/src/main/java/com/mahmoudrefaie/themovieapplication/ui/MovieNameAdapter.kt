package com.mahmoudrefaie.themovieapplication.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.mahmoudrefaie.themovieapplication.R
import com.mahmoudrefaie.themovieapplication.model.MovieItem
import kotlinx.android.synthetic.main.movie_name_item.view.*
import org.w3c.dom.Text

class MovieNameAdapter(val context: Context, val grid : List<MovieItem>) : BaseAdapter() {

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {

        val view:View = LayoutInflater.from(context).inflate(R.layout.movie_name_item,parent,false)

        val movie_name : TextView = view.findViewById(R.id.movie_name)

        var listItem : MovieItem = grid.get(position)

        movie_name.text = listItem.title

        return view
    }

    override fun getItem(position: Int): Any {
        return grid.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return grid.size
    }
}