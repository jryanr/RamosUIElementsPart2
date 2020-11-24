package com.example.ramosuielements2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView

class AlbumsActivity : AppCompatActivity() {
    var modalList = ArrayList<Modal>()
    var names = arrayOf("image1","image2","image3")
    var images = intArrayOf(R.drawable.image1,R.drawable.image2,R.drawable.image3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)

        for(i in names.indices){
            modalList.add(Modal(names[i],images[i]))
        }
        var customAdapter = CustomAdapter(modalList, this);
        val gridView = findViewById<GridView>(R.id.gridView)
        gridView.adapter=customAdapter;

        gridView.setOnItemClickListener(){adapterView, view, i, l ->
            var intent = Intent(this, AlbumDetailsActivity::class.java)
            intent.putExtra("data",modalList[i])
            startActivity(intent);
        }
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
            //tvImageName?.text = itemModel[position].name;
            if(position==0) {
                tvImageName?.text = "Franco"
            }
            else if(position==1){
                tvImageName?.text = "Linkin Park"
            }
            else if(position==2){
                tvImageName?.text = "Fall Out Boy"
            }
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
}