package com.example.healthswu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test.RecordFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

lateinit var bottomNavigationView: BottomNavigationView
@Suppress("DEPRECATION")
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        bottomNavigationView = findViewById(R.id.mainNav)

        // 바텀네비게이션 변수 실행
        bottomNavigationView.setOnNavigationItemSelectedListener(/* listener = */
            onBottomNavItemSelectedListener)

        // 처음 뜨는 fragment 설정
        bottomNavigationView.setSelectedItemId(R.id.navigation_3)
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

                R.id.navigation_3 -> {
                    val fragment3 = RecordFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.listFrame, fragment3)
                        .addToBackStack(null)
                        .commit()
                }
            }
            true
        }



}