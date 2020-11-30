package com.example.ramosuielements2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import com.example.ramosuielements2.models.Album
import com.example.ramosuielements2.models.Song

class AlbumsActivity : AppCompatActivity() {
    var modalList = ArrayList<Modal>()
    var names = arrayOf("Franco","Linkin Park","Fall Out Boy")
    var images = intArrayOf(R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4)
    var titlesArray: ArrayList<String> = ArrayList()
    lateinit var albumsTableHandler: AlbumsTableHandler
    lateinit var albums: MutableList<Album>
    lateinit var adapter: ArrayAdapter<String>
    lateinit var titles : MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        albumsTableHandler = AlbumsTableHandler(this)
        albums = albumsTableHandler.read()
        titles = albumsTableHandler.getTitles()




        for(i in names){
            titlesArray.add(i)
        }

        for (string in titles){
            titlesArray.add(string)
        }

        var h1 = intent.getStringArrayListExtra("francoList")
        var h2 = intent.getStringArrayListExtra("lpList")
        var h3 = intent.getStringArrayListExtra("fobList")
        var h4 = intent.getStringArrayListExtra("firstList")
        var h5 = intent.getStringArrayListExtra("secondList")


        for(i in titlesArray.indices){
            if(i<3){
                modalList.add(Modal(titlesArray[i], images[i]))
            }
            else if(i>2){
                modalList.add(Modal(titlesArray[i],images[3]))
            }
        }

        var customAdapter = CustomAdapter(modalList, this);
        val gridView = findViewById<GridView>(R.id.gridView)
        gridView.adapter=customAdapter;

        gridView.setOnItemClickListener(){adapterView, view, i, l ->
            var intent = Intent(this, AlbumDetailsActivity::class.java)
            intent.putExtra("data",modalList[i])
            intent.putStringArrayListExtra("francoList", h1)
            intent.putStringArrayListExtra("lpList", h2)
            intent.putStringArrayListExtra("fobList", h3)
            intent.putStringArrayListExtra("firstList", h4)
            intent.putStringArrayListExtra("secondList", h5)
            startActivity(intent);
        }
        registerForContextMenu(gridView)
    }

    class CustomAdapter(var itemModel: ArrayList<Modal>, var context: Context): BaseAdapter(){
        var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view = convertView;
            if(view==null){
                view =layoutInflater.inflate(R.layout.row_items,parent,false);
            }
            var tvImageName = view?.findViewById<TextView>(R.id.imageName)
            var imageView = view?.findViewById<ImageView>(R.id.imageView);
            tvImageName?.text = itemModel[position].name;
            /*if(position==0) {
                tvImageName?.text = "Franco"
                imageView?.setImageResource(itemModel[position].image!!)
            }
            else if(position==1){
                tvImageName?.text = "Linkin Park"
                imageView?.setImageResource(itemModel[position].image!!)
            }
            else if(position==2){
                tvImageName?.text = "Fall Out Boy"
                imageView?.setImageResource(itemModel[position].image!!)
            }
            else if(position == 3){
                tvImageName?.text = "New Album"
                imageView?.setImageResource(itemModel[3].image!!)
            }*/
            imageView?.setImageResource(itemModel[position].image!!)
            return view!!;
        }
        override fun getCount(): Int {
            return itemModel.size
        }

        override fun getItem(position: Int): Any {
            return itemModel[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater =menuInflater
        inflater.inflate(R.menu.album_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when(item.itemId){
            R.id.go_to_songs_act -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.create_album ->{
                startActivity(Intent(this, CreateAlbumActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.album_context_menu, menu)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId){
            R.id.edit_album -> {
                val album_id = albums[info.position-3].id
                val intent = Intent(applicationContext, EditAlbumActivity::class.java)
                intent.putExtra("album_id",album_id)
                startActivity(intent)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}