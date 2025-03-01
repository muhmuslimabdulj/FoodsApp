/*
* Author : M Muslim Abdul J
* */

package com.mmuslimabdulj.foodsapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mmuslimabdulj.foodsapp.R
import com.mmuslimabdulj.foodsapp.adapter.CarouselAdapter
import com.mmuslimabdulj.foodsapp.adapter.NewsAdapter
import com.mmuslimabdulj.foodsapp.model.DataNews
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recData: RecyclerView
//    private lateinit var imgCover: ImageView // change to carousel
    private lateinit var vp2: ViewPager2
    private var postAdapter: NewsAdapter? = null
    private var carouselAdapter: CarouselAdapter? = null
    private var newsList: ArrayList<DataNews> = ArrayList()
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        imgCover = findViewById(R.id.img_cover) // change to carousel
        recData = findViewById(R.id.rec_data)
        vp2 = findViewById(R.id.vp2_Carousel)
        recData.setHasFixedSize(true)
        recData.layoutManager = LinearLayoutManager(this)
        loadDataNews()
        autoScrollCarousel()
    }

    private fun loadJSONFromAsset(): String? {
        return try {
            val inputStream = assets.open("news.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    private fun loadDataNews() {
        try {
            val jsonObject = JSONObject(loadJSONFromAsset())
            val jsonArray = jsonObject.getJSONArray("DATA_NEWS")
            for (i in 0 until jsonArray.length()) {
                val jo = jsonArray.getJSONObject(i)
                val news = DataNews(
                    title = jo.getString("title_news"),
                    linkImage = jo.getString("image_link"),
                    description = jo.getString("link_news")
                )
                newsList.add(news)
            }

            // Change to Carousel
//            val randomIndex = Random.nextInt(newsList.size)
//            val coverNews = newsList[randomIndex]
//            Picasso.get().load(coverNews.linkImage).into(imgCover)
//            imgCover.setOnClickListener {
//                Constans.title = coverNews.title
//                Constans.image = coverNews.linkImage
//                Constans.description = coverNews.description
//                val intent = Intent(this, NewsActivity::class.java)
//                startActivity(intent)
//            }

            postAdapter = NewsAdapter(newsList, this)
            recData.adapter = postAdapter

            carouselAdapter = CarouselAdapter(newsList)
            vp2.adapter = carouselAdapter
        } catch (e: JSONException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun autoScrollCarousel() {
        val DELAY_TIME: Long = 3000
        val runnable = object : Runnable {
            override fun run() {
                if (carouselAdapter != null) {
                    currentPage = (currentPage + 1) % newsList.size
                    vp2.setCurrentItem(currentPage, true)
                    handler.postDelayed(this, DELAY_TIME)
                }
            }
        }
        handler.post(runnable)
    }
}