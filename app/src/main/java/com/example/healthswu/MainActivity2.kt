package com.example.healthswu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

lateinit var bottomNavigationView: BottomNavigationView
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        bottomNavigationView = findViewById(R.id.mainNav)

        // 바텀네비게이션 변수 실행
        bottomNavigationView.setOnNavigationItemSelectedListener(onBottomNavItemSelectedListener)

        // 처음 뜨는 fragment 설정
        bottomNavigationView.setSelectedItemId(R.id.navigation_1)
    }

        private val onBottomNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.navigation_1 -> {
                    val fragment1 = FoodlistFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.listFrame, fragment1)
                        .commit()
                }

                R.id.navigation_2 -> {
                    val fragment2 = frag_training_rec()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.listFrame, fragment2)
                        .commit()
                }
            }
            true
        }



}