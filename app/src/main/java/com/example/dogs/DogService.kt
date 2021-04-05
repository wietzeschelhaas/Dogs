package com.example.dogs

import android.app.Activity
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class DogService {

    //Returns dogs in json format
    fun getDogs(): ArrayList<Dog> {
        val url = "https://dog.ceo/api/breeds/image/random/50"

        // do your stuff
        var result: String
        val connection = URL(url).openConnection() as HttpURLConnection
        try {
            connection.connect()
            result = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
        } finally {
            connection.disconnect()
        }
        val answer = JSONObject(result)
        val imageUrls =  answer.getJSONArray("message")

        val dogs = ArrayList<Dog>()

        for( i in 0..imageUrls.length()-1){
            val dogNumber = i + 1
            val dog = Dog(imageUrls.get(i)as String, "Dog " + dogNumber)
            dogs.add(dog)
        }

        return dogs
    }

}
