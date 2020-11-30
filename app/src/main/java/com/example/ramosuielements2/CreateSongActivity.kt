package com.example.ramosuielements2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ramosuielements2.models.Song

class CreateSongActivity : AppCompatActivity() {
    lateinit var addSongButton: Button
    lateinit var titleET: EditText
    lateinit var artistET: EditText
    lateinit var albumET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_song)

        val databaseHandler = SongsTableHandler(this)
        titleET = findViewById(R.id.titleET)
        artistET = findViewById(R.id.artistET)
        albumET = findViewById(R.id.albumET)

        addSongButton = findViewById(R.id.addSongButton)
        addSongButton.setOnClickListener{
            val title = titleET.text.toString()
            val artist = artistET.text.toString()
            val album = albumET.text.toString()

            val song = Song(title = title,artist = artist,album = album)


            if(databaseHandler.create(song)){
                Toast.makeText(applicationContext, "Song was created.", Toast.LENGTH_SHORT).show()

            } else {

                Toast.makeText(applicationContext, "Oops, something went wrong.", Toast.LENGTH_SHORT).show()
            }
            clearFields()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    fun clearFields(){
        titleET.text.clear()
        artistET.text.clear()
        albumET.text.clear()
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
            else -> super.onOptionsItemSelected(item)
        }

    }
}