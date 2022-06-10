package com.example.foodrecipe.adapter

import android.app.Activity
import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.foodrecipe.R
import com.example.foodrecipe.databinding.RecyclerListRowBinding
import com.example.foodrecipe.model.RecyclerData


class RecipeAdapter() : RecyclerView.Adapter<RecipeAdapter.MyViewHolder>() {
    //arraylist to hold the data which need to be displayed on Recycler view
    var items = ArrayList<RecyclerData>()
    lateinit   var layoutinflater: LayoutInflater

     lateinit  var context: Context

    fun setUpdatedData(items: ArrayList<RecyclerData>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: RecyclerListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {


        val imageThumb = binding.imageThumb

        val title = binding.title

        //to get recycler data
        fun bind(data: RecyclerData) {


            title.text = data.title
            Log.d("TAG6", "" + title)
            val url = data.image

//            Glide.with()
//                .load(url)
//                .into(imageThumb)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding =
            RecyclerListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        context= holder.itemView.context
        holder.bind(items.get(position))
        Log.d("TAG4", "" + items)
    }
    override fun getItemCount(): Int {
        return items.size
    }
}