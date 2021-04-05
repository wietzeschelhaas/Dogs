package com.example.dogs

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class  RecyclerViewAdapter(private val dogs : ArrayList<Dog>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image : ImageView
        var title : TextView
        var textDesc : TextView

        init {
            image = itemView.findViewById(R.id.image_dog_card)
            title = itemView.findViewById(R.id.text_title_dog_card)
            textDesc = itemView.findViewById(R.id.text_description_dog_card)

            itemView.setOnClickListener { v:View->
                val index : Int = adapterPosition
                //start new activity with detailed view of dog
                val intent = Intent(v.context,DetailActivity::class.java)
                intent.putExtra("imageURL",dogs[index].imageURL)
                intent.putExtra("detailDesc",dogs[index].detailedDesc)
                intent.putExtra("title",dogs[index].title)

                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.dog_card,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dogNumber = position + 1
        holder.title.text = dogs[position].title
        holder.textDesc.text = dogs[position].desc

        //init picasso
        val picasso = Picasso.get()

        picasso.load(dogs[position].imageURL)
            .into(holder.image)



    }


    override fun getItemCount(): Int {
        return dogs.size
    }
}