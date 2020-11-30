package com.example.ramosuielements2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ramosuielements2.models.Song

class EditSongActivity : AppCompatActivity() {
    lateinit var updateSongButton: Button
    lateinit var editTitleET: EditText
    lateinit var editArtistET: EditText
    lateinit var editAlbumET: EditText
    lateinit var song: Song

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_song)

        val song_id = intent.getIntExtra("song_id",0)

        val databaseHandler = SongsTableHandler(this)
        song = databaseHandler.readOne(song_id)

        editTitleET = findViewById(R.id.editSongTitleET)
        editArtistET = findViewById(R.id.editSongArtistET)
        editAlbumET = findViewById(R.id.editAlbumET)
        updateSongButton = findViewById(R.id.updateSongButton)

        editTitleET.setText(song.title)
        editArtistET.setText(song.artist)
        editAlbumET.setText(song.album)

        updateSongButton.setOnClickListener{
            val title = editTitleET.text.toString()
            val artist = editArtistET.text.toString()
            val album = editAlbumET.text.toString()

            val updated_song = Song(id=song.id,title = title,artist = artist,album = album)


            if(databaseHandler.update(updated_song)){
                Toast.makeText(applicationContext, "Song was updated.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            } else {

                Toast.makeText(applicationContext, "Oops, something went wrong.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}