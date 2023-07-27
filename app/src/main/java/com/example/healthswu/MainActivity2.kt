package com.example.healthswu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val fragment1 = FoodlistFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.listFrame, fragment1)
            .commit()
    }
}