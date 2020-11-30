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
import com.example.ramosuielements2.models.Album

class CreateAlbumActivity : AppCompatActivity() {

    lateinit var createAlbumButton: Button
    lateinit var titleET: EditText
    lateinit var releaseDateET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_album)

        val databaseHandler = AlbumsTableHandler(this)
        titleET = findViewById(R.id.albumTitleET)
        releaseDateET = findViewById(R.id.releaseDateET)

        createAlbumButton = findViewById(R.id.createAlbumButton)
        createAlbumButton.setOnClickListener{
            val title = titleET.text.toString()
            val releaseDate = releaseDateET.text.toString()

            val album = Album(title = title,release_date = releaseDate)

            if(databaseHandler.create(album)){
                Toast.makeText(applicationContext, "Album was created.", Toast.LENGTH_SHORT).show()

            } else {

                Toast.makeText(applicationContext, "Oops, something went wrong.", Toast.LENGTH_SHORT).show()
            }
            clearFields()
            startActivity(Intent(this, AlbumsActivity::class.java))
        }
    }
    fun clearFields(){
        titleET.text.clear()
        releaseDateET.text.clear()
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