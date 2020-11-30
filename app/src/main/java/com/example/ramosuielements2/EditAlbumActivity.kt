package com.example.ramosuielements2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ramosuielements2.models.Album


class EditAlbumActivity : AppCompatActivity() {
    lateinit var updateAlbumButton: Button
    lateinit var ediAlbumtTitleET: EditText
    lateinit var editReleaseDateET: EditText
    lateinit var album: Album

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_album)

        val album_id = intent.getIntExtra("album_id",0)

        val databaseHandler = AlbumsTableHandler(this)
        album = databaseHandler.readOne(album_id)

        ediAlbumtTitleET = findViewById(R.id.editAlbumTitleET)
        editReleaseDateET = findViewById(R.id.editReleaseDateET)
        updateAlbumButton = findViewById(R.id.updateAlbumButton)

        ediAlbumtTitleET.setText(album.title)
        editReleaseDateET.setText(album.release_date)

        updateAlbumButton.setOnClickListener{
            val title = ediAlbumtTitleET.text.toString()
            val release_date = editReleaseDateET.text.toString()

            val updated_album = Album(id=album.id,title = title,release_date = release_date)


            if(databaseHandler.update(updated_album)){
                Toast.makeText(applicationContext, "Album was updated.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "Oops, something went wrong.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}