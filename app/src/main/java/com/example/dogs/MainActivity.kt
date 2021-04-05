package com.example.dogs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    private var layoutManager : RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>? = null

    private var dogService = DogService()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Swipe down to refresh fills the recyclerView with a new set of dogs.
        swipeContainer.setOnRefreshListener {
            fillWithDogs()
            swipeContainer.isRefreshing = false
        }

        fillWithDogs()
    }

    private fun fillWithDogs() {
        Thread(Runnable {
            var dogs = dogService.getDogs()
            //Fill the recyclerview on the ui thread.
            runOnUiThread {
                layoutManager = LinearLayoutManager(this)

                //Set spanCount to 2 since we want 2 columns
                dogList.setLayoutManager(GridLayoutManager(this, 2))
                dogList.adapter = RecyclerViewAdapter(dogs)
            }
        }).start()
    }
}