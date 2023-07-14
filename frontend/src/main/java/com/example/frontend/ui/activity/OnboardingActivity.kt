package com.example.frontend.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.frontend.R

class OnboardingActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var imageList: List<Int>

    @SuppressLint("AppCompatMethod")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        actionBar?.hide()
        var go = findViewById<Button>(R.id.start)
        go.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        viewPager = findViewById(R.id.idViewPager)

        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.img_onboarding01
        imageList = imageList + R.drawable.img_onboarding02
        imageList = imageList + R.drawable.img_onboarding03

        viewPagerAdapter = ViewPagerAdapter(this@OnboardingActivity, imageList)


        viewPager.adapter = viewPagerAdapter

    }
}