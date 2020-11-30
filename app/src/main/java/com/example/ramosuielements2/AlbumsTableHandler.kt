package com.example.ramosuielements2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.ramosuielements2.models.Album

class AlbumsTableHandler (var context: Context): SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "albums_database"
        private val TABLE_NAME = "albums"
        private val COL_ID = "id"
        private val COL_TITLE = "title"
        private val COL_RELEASE_DATE = "release_date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE "+ TABLE_NAME+"("+ COL_ID+" INTEGER PRIMARY KEY, "+ COL_TITLE+" TEXT, "+ COL_RELEASE_DATE+" TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun create(album: Album): Boolean {
        val database = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COL_TITLE, album.title)
        contentValues.put(COL_RELEASE_DATE, album.release_date)

        val result = database.insert(TABLE_NAME, null, contentValues)
        if(result == (0).toLong()){
            return false
        }
        return true
    }
    fun update(album: Album): Boolean{
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_TITLE, album.title)
        contentValues.put(COL_RELEASE_DATE, album.release_date)

        val result = database.update(TABLE_NAME, contentValues, "id="+album.id, null)
        if(result == 0){
            return false
        }
        return true
    }
    fun delete(album: Album): Boolean{
        val database = this.writableDatabase
        val result = database.delete(TABLE_NAME, "id=${album.id}",null)
        if(result == 0){
            return false
        }
        return true
    }

    fun read(): MutableList<Album>{
        val albumsList: MutableList<Album> = ArrayList<Album>()
        val query = "SELECT * FROM " + TABLE_NAME
        val database = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor =  database.rawQuery(query, null)
        } catch(e: SQLiteException){
            return albumsList
        }
        var id: Int
        var title: String
        var release_date: String
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                release_date = cursor.getString(cursor.getColumnIndex(COL_RELEASE_DATE))
                val album = Album(id,title,release_date)
                albumsList.add(album)
            }while(cursor.moveToNext())
        }
        return albumsList
    }
    fun readOne(album_id: Int): Album {
        var album: Album = Album(0,"","")
        val query = "SELECT * FROM  $TABLE_NAME WHERE id = $album_id"
        val database = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor =  database.rawQuery(query, null)
        } catch(e: SQLiteException){
            return album
        }
        var id: Int
        var title: String
        var release_date: String

        if(cursor.moveToFirst()){
            id = cursor.getInt(cursor.getColumnIndex(COL_ID))
            title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
            release_date = cursor.getString(cursor.getColumnIndex(COL_RELEASE_DATE))
            album = Album(id,title,release_date)


        }
        return album
    }
    fun getTitles(): MutableList<String>{
        val albumsList: MutableList<String> = ArrayList<String>()
        val query = "SELECT * FROM " + TABLE_NAME
        val database = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor =  database.rawQuery(query, null)
        } catch(e: SQLiteException){
            return albumsList
        }
        var title: String
        if(cursor.moveToFirst()){
            do{
                title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                albumsList.add(title)
            }while(cursor.moveToNext())
        }
        return albumsList
    }
}
