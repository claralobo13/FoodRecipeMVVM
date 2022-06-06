package com.example.foodrecipe.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipe.R
import com.example.foodrecipe.model.RecyclerData
import com.squareup.picasso.Picasso

class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.MyViewHolder>() {
      //arraylist to hold the data which need to be displayed on Recycler view
    var items= ArrayList<RecyclerData>()

    fun setUpdatedData(items : ArrayList<RecyclerData>){
        this.items= items
        notifyDataSetChanged()


    }
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageThumb= view.findViewById<ImageView>(R.id.imageThumb)
        val title= view.findViewById<TextView>(R.id.title)

        //to get recycler data
        fun bind(data: RecyclerData){

            title.text= data.title
            Log.d("TAG6", ""+title)
            val url= data.image
            Picasso.get()
                .load(url)
                .into(imageThumb)



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            //inflate layout
        val layout= LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row,parent,false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position))
        Log.d("TAG4", ""+items)
    }

    override fun getItemCount(): Int {
         return items.size
    }
}