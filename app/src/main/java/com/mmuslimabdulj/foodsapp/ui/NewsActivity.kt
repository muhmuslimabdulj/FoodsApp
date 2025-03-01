/*
* Author : M Muslim Abdul J
* */

package com.mmuslimabdulj.foodsapp.ui

import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mmuslimabdulj.foodsapp.R
import com.mmuslimabdulj.foodsapp.util.Constans
import com.squareup.picasso.Picasso

class NewsActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var imgDetail: ImageView
    private lateinit var txtTitleDetail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imgDetail = findViewById(R.id.img_detail)
        txtTitleDetail = findViewById(R.id.tv_title_detail)
        webView = findViewById(R.id.wv_news)

        txtTitleDetail.text = Constans.title

        Picasso.get().load(Constans.image).into(imgDetail)

        webView.loadDataWithBaseURL(
            null,
            "<head><style>img{max-width: 90%; width:auto; height:auto;}</style></head>" + Constans.description,
            "text/html",
            "UTF-8",
            null
        )
    }
}