package com.example.ramosuielements2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class AlbumDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)
        var modalItems: Modal = intent.getSerializableExtra("data") as Modal;

        Log.e("name",modalItems.name.toString());
        val viewName = findViewById<TextView>(R.id.viewName)
        val viewImage = findViewById<ImageView>(R.id.viewImage)

        viewName.text=modalItems.name;
        var songsArray: Array<String> = arrayOf()

        if(modalItems.name.equals("image1")) {
                viewName.text = "Franco"
                songsArray = arrayOf("Better Days", "Castaway", "Manipulator",
                                     "Aurora Sunrise", "Song for The Suspect", "A Beautiful Diversion",
                                     "This Gathering", "To Survive", "Memory Kill",
                                     "Lost In Your Universe", "For My Dearly Departed", "Mondaze",
                )
        }
        else if(modalItems.name.equals("image2")) {
            viewName.text = "Linkin Park"
            songsArray = arrayOf("In the End", "Numb", "What I've Done",
                                 "One Step Closer", "Burn It Down", "Crawling",
                                 "Bleed it Out", "One More Light", "Papercut",
                                 "With You", "Points of Authority", "By Myself"
                    )
        }
        else if(modalItems.name.equals("image3")) {
            viewName.text = "Fall Out Boy"
            songsArray = arrayOf("Grand Theft Autumn", "Thnks fr The Mmrs", "Sugar, We're Goin Down",
                                 "Centuries", "Summer Days", "Dance, Dance",
                                 "Immortals", "I've Been Waiting", "The Phoenix",
                                 "Uma Thurman", "Young Volcanoes", "Alone Together"
            )
        }
        viewImage.setImageResource(modalItems.image!!);



        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
        val songs_list: ListView = findViewById<ListView>(R.id.songs_list)
        songs_list.adapter = adapter


    }
}