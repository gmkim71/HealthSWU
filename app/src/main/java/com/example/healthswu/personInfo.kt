package com.example.healthswu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class personInfo : AppCompatActivity() {

    private lateinit var savebtn: Button
    private lateinit var sexEdit: EditText
    private lateinit var heightEdit: EditText
    private lateinit var weightEdit: EditText
    private lateinit var goalEdit: EditText
    private lateinit var detailsEdit: EditText
    private lateinit var dbHelp: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_info)

        dbHelp = DBHelper(this)

        // Find views by their IDs
        savebtn = findViewById(R.id.saveButton)
        sexEdit = findViewById(R.id.sexEditText)
        heightEdit = findViewById(R.id.heightEditText)
        weightEdit = findViewById(R.id.weightEditText)
        goalEdit = findViewById(R.id.goalEditText)
        detailsEdit = findViewById(R.id.detailsEditText)

        // 이전 LoginActivity의 인텐트로부터 username과 password 값을 가져오기
        val username = intent.getStringExtra("username")
        val password = intent.getStringExtra("password")

        // Save Button click listener
        savebtn.setOnClickListener {
            // Get user input data from EditText fields
            val sex = sexEdit.text.toString()
            val heightStr = heightEdit.text.toString()
            val weightStr = weightEdit.text.toString()
            val goal = goalEdit.text.toString()
            val details = detailsEdit.text.toString()

            // Convert height and weight to Double (if applicable)
            val height = if (heightStr.isNotEmpty()) heightStr.toDouble() else null
            val weight = if (weightStr.isNotEmpty()) weightStr.toDouble() else null

            // Get username and password from the previous LoginActivity's intent
            val username1 = intent.getStringExtra("username")
            val password1 = intent.getStringExtra("password")

            // Save the user data in the database using DBHelper
            val isDataInserted = dbHelp.insertdata(username1, password1, sex, height, weight, goal, details)

            if (isDataInserted) {
                Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to save data!", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(applicationContext, MainActivity2::class.java)
            startActivity(intent)

        }
    }
}
