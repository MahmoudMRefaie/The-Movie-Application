package com.mahmoudrefaie.themovieapplication.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import com.mahmoudrefaie.themovieapplication.R

data class Movie(var title:String , val overview : String , val original_language : String , val popularity : String , val release_date : String){

    constructor(title:String) : this(title,"no", "No_Lang", "no", "no"){
        this.title = title
    }

}
/*
data class Movie(var api_key:String , var language:String , var page:Int , var region:String){

    constructor(api_key:String) : this(api_key,"no", 0, "no"){
        this.api_key = api_key
    }
}
 */
