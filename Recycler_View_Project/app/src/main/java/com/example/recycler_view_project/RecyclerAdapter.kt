package com.example.recycler_view_project


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.card_layout.view.*


class RecyclerAdapter(val itemList: ArrayList<Model>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder> (){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int {
        return  itemList.size
           }
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        Picasso.get().load(itemList[position].image).into(holder.itemImage)
//        holder.itemImage.setImageResource(itemList[position].image)
        holder.itemTitle.setText(itemList[position].description)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView


        init {
                itemImage= itemView.item_image
             itemTitle= itemView.item_title

            itemView.setOnClickListener {

                val position: Int =adapterPosition
                Toast.makeText(itemView.context,"click on title",Toast.LENGTH_LONG).show()
            }
        }
    }

    }



