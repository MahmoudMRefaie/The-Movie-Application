package com.mahmoudrefaie.themovieapplication.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import com.mahmoudrefaie.themovieapplication.R

data class Movie(var title:String , var overview : String , var original_language : String , var popularity : String , var release_date : String){

    constructor(title:String) : this(title,"no", "No_Lang", "no", "no"){
        this.title = title
    }

}
