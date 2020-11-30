package com.example.ramosuielements2

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ramosuielements2.models.Song
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    val queuedList = ArrayList<String>()
    val francoList = ArrayList<String>()
    val lpList = ArrayList<String>()
    val fobList = ArrayList<String>()
    val firstList = ArrayList<String>()
    val secondList = ArrayList<String>()
    var songsArray: ArrayList<String> = ArrayList()
    var index = -1


    lateinit var songsListView: ListView
    lateinit var songsTableHandler: SongsTableHandler
    lateinit var songs: MutableList<Song>
    lateinit var adapter: ArrayAdapter<String>
    lateinit var titles : MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        songsTableHandler = SongsTableHandler(this)
        songs = songsTableHandler.read()
        titles = songsTableHandler.getTitles()

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

        for (string in titles){
            songsArray.add(string)
        }

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
        songsListView = findViewById<ListView>(R.id.songsListView)
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
                val intent = Intent(this, AlbumsActivity::class.java)
                intent.putStringArrayListExtra("francoList", francoList)
                intent.putStringArrayListExtra("lpList", lpList)
                intent.putStringArrayListExtra("fobList", fobList)
                intent.putStringArrayListExtra("firstList", firstList)
                intent.putStringArrayListExtra("secondList", secondList)
                startActivity(intent)
                true
            }
            R.id.go_to_queue_act -> {
                val intent = Intent(this, QueuedSongsActivity::class.java)
                intent.putStringArrayListExtra("queuedList", queuedList)
                startActivity(intent)
                true
            }
            R.id.create_song -> {
                startActivity(Intent(this, CreateSongActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.song_actions_menu, menu)

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
            R.id.edit_song -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val song_id = songs[info.position - 18].id
                val intent = Intent(applicationContext, EditSongActivity::class.java)
                intent.putExtra("song_id", song_id)
                startActivity(intent)
                true
            }
            R.id.delete_song -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val song = songs[info.position - 18]
                if (songsTableHandler.delete(song)) {
                    songsArray.removeAt(info.position)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(applicationContext, "Song was deleted.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Oops, something went wrong.", Toast.LENGTH_SHORT).show()
                }
                true
            }
            R.id.add_to_album -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                index = info.position

                true
            }
            R.id.Franco -> {
                francoList.add(songsArray[index])
                true
            }
            R.id.LinkinPark -> {
                lpList.add(songsArray[index])
                true
            }
            R.id.FallOutBoy -> {
                fobList.add(songsArray[index])
                true
            }

            R.id.first_album -> {
                firstList.add(songsArray[index])
                true
            }
            R.id.second_album -> {
                secondList.add(songsArray[index])
                true
            }

            else -> super.onContextItemSelected(item)
        }


    }

}










