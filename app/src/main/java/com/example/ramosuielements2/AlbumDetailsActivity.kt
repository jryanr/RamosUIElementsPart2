package com.example.ramosuielements2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.GREEN
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import java.lang.StringBuilder


class AlbumDetailsActivity : AppCompatActivity() {
    var albumSongsArray: ArrayList<String> = ArrayList()
    lateinit var adapter: ArrayAdapter<String>






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        var modalItems: Modal = intent.getSerializableExtra("data") as Modal;

        Log.e("name", modalItems.name.toString());
        val viewName = findViewById<TextView>(R.id.viewName)
        val viewImage = findViewById<ImageView>(R.id.viewImage)

        var holder: Array<String> = arrayOf()

        viewName.text = modalItems.name;
        var songsArray: Array<String> = arrayOf()
        if (modalItems.name.equals("image1")) {
            viewName.text = "Franco"
            holder  = arrayOf("Better Days", "Castaway", "Manipulator",
                    "Aurora Sunrise", "Song for The Suspect", "A Beautiful Diversion",
                    "This Gathering", "To Survive", "Memory Kill",
                    "Lost In Your Universe", "For My Dearly Departed", "Mondaze",
            )
            for (string in holder){
                albumSongsArray.add(string)
            }
        } else if (modalItems.name.equals("image2")) {
            viewName.text = "Linkin Park"
            holder = arrayOf("In the End", "Numb", "What I've Done",
                    "One Step Closer", "Burn It Down", "Crawling",
                    "Bleed it Out", "One More Light", "Papercut",
                    "With You", "Points of Authority", "By Myself"
            )
            for (string in holder){
                albumSongsArray.add(string)
            }
        } else if (modalItems.name.equals("image3")) {
            viewName.text = "Fall Out Boy"
            holder = arrayOf("Grand Theft Autumn", "Thnks fr The Mmrs", "Sugar, We're Goin Down",
                    "Centuries", "Summer Days", "Dance, Dance",
                    "Immortals", "I've Been Waiting", "The Phoenix",
                    "Uma Thurman", "Young Volcanoes", "Alone Together"
            )
            for (string in holder){
                albumSongsArray.add(string)
            }
        }

        viewImage.setImageResource(modalItems.image!!);
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, albumSongsArray)
        val songs_list: ListView = findViewById<ListView>(R.id.songs_list)

        songs_list.adapter = adapter

        registerForContextMenu(songs_list)


    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.album_item_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.remove -> {
                val dialogBuilder = AlertDialog.Builder(this)

                dialogBuilder.setMessage("Do you want to remove song?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", DialogInterface.OnClickListener{
                            dialog, which ->
                            val info = item.getMenuInfo() as AdapterView.AdapterContextMenuInfo
                            val index = info.position
                            albumSongsArray.removeAt(index);

                            val toast = Toast.makeText(applicationContext, "Song has been removed", Toast.LENGTH_SHORT)
                            adapter.notifyDataSetChanged();
                            toast.show()
                        })
                        .setNegativeButton("No",DialogInterface.OnClickListener{
                            dialog, which->
                            dialog.cancel()
                        })
                val alert: AlertDialog = dialogBuilder.create()
                alert.setTitle("Remove Song")
                alert.show()
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }

}

