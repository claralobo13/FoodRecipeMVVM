package com.example.foodrecipe.adapter

import android.annotation.SuppressLint
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

import com.example.foodrecipe.databinding.RecyclerListRowBinding
import com.example.foodrecipe.model.RecyclerData



//arraylist to hold the data which need to be displayed on Recycler view - var items = ArrayList<RecyclerData>()
//fun bind - to get recycler data
@SuppressLint("StaticFieldLeak")
lateinit var activity: Activity
class RecipeAdapter(fragmentActivity: FragmentActivity?) : RecyclerView.Adapter<RecipeAdapter.MyViewHolder>() {

    var items = ArrayList<RecyclerData>()


     lateinit  var context: Context

     init {
         if(fragmentActivity !=null){
             activity=fragmentActivity
         }
     }

    fun setUpdatedData(items: ArrayList<RecyclerData>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: RecyclerListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {


        val imageThumb = binding.imageThumb

        val title = binding.title

        fun bind(data: RecyclerData) {


            title.text = data.title
            Log.d("TAG", "" + title)
            val url = data.image

            Glide.with(activity)
                        .load(url)
                        .into(imageThumb)



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
        Log.d("TAG2", "Dispaly response" + items)
    }
    override fun getItemCount(): Int {
        return items.size
    }
}