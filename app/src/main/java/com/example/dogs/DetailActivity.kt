package com.example.dogs

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)


        val imageUrl = intent.getStringExtra("imageURL")
        val detailsDesc = intent. getStringExtra("detailDesc")
        val title = intent. getStringExtra("title")

        val detailedImageView = findViewById<ImageView>(R.id.image_dog_detail)
        val detailedDescription = findViewById<TextView>(R.id.text_description_detail)
        val detailedTitle = findViewById<TextView>(R.id.text_title_detail)

        detailedDescription.text = detailsDesc
        detailedTitle.text = title


        //init picasso
        val picasso = Picasso.get()

        picasso.load(imageUrl).into(detailedImageView)


    }
}