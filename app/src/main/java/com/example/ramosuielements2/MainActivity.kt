package com.example.ramosuielements2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    val queuedList = ArrayList<String>()
    var songsArray: ArrayList<String> = ArrayList()

    lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val holder  = arrayOf(
                "Better Days", "Castaway", "Manipulator",
                "In the End", "Numb", "What I've Done",
                "Grand Theft Autumn", "Thnks fr The Mmrs", "Sugar, We're Goin Down",
                "Welcome to the Black Parade", "Helena", "I Don't Love You",
                "Halik", "Huling Sayaw", "Tagpuan",
                "Perfect", "Summer Paradies", "Welcome To My Life"
        )
        for (string in holder){
            songsArray.add(string)
        }

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
        val songsListView: ListView = findViewById<ListView>(R.id.songsListView)
        songsListView.adapter = adapter
        registerForContextMenu(songsListView)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater =menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when(item.itemId){
            R.id.go_to_songs_act -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.go_to_albums_act -> {
                startActivity(Intent(this, AlbumsActivity::class.java))
                true
            }
            R.id.add_to_queue_act -> {
                val intent = Intent(this, QueuedSongsActivity::class.java)
                intent.putStringArrayListExtra("queuedList", queuedList)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.song_item_menu, menu)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {


        return when (item.itemId){
            R.id.add_to_queue -> {
                val info = item.getMenuInfo() as AdapterContextMenuInfo
                val index = info.position
                queuedList.add(songsArray[index])

                val snackbar = Snackbar.make(findViewById(android.R.id.content), "Go to QueuedSongs Activity", Snackbar.LENGTH_LONG)
                snackbar.setAction("Go", View.OnClickListener{
                    val intent = Intent(this, QueuedSongsActivity::class.java)
                    intent.putStringArrayListExtra("queuedList", queuedList)
                    startActivity(intent)
                })
                snackbar.show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }



}










