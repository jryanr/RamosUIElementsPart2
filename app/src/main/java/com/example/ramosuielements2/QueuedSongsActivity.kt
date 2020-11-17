package com.example.ramosuielements2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class QueuedSongsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)

        val intent = intent
        var queuedList = intent.getStringArrayListExtra("queuedList")


        val songsArray: Array<String> = queuedList!!.toTypedArray()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
        val queuedSongsListView: ListView = findViewById<ListView>(R.id.listView)
        queuedSongsListView.adapter = adapter


    }
}