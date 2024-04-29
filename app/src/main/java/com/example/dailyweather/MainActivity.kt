package com.example.dailyweather

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RatingBar
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.dailyweather.databinding.ActivityMainBinding
import com.example.mytabswipe.SecondFragment
import com.google.android.material.tabs.TabLayout


//13176c001cf7c033212df14e39d59c3d

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val fragments = listOf(FirstFragment(), SecondFragment()) // Replace with your fragment instances
        val adapter = ViewPagerAdapter(fragments, supportFragmentManager,lifecycle)
        viewPager.adapter = adapter
    }

}