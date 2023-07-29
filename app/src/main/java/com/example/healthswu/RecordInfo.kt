package com.example.healthswu

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.test.recordDBManager

class RecordInfo : AppCompatActivity() {
    lateinit var tvExerciseName : TextView
    lateinit var  tvExerciseType: TextView
    lateinit var  tvExerciseDate: TextView
    lateinit var  tvExerciseCount: TextView
    lateinit var  tvExerciseSet: TextView
    lateinit var  tvExerciseWeight: TextView
    lateinit var btnDelete : Button

    lateinit var dbManager: recordDBManager
    lateinit var sqlitedb : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_info)

        tvExerciseName=findViewById(R.id.tvExerciseName)
        tvExerciseType=findViewById(R.id.tvExerciseType)
        tvExerciseDate=findViewById(R.id.tvExerciseDate)
        tvExerciseCount=findViewById(R.id.tvExerciseCount)
        tvExerciseSet=findViewById(R.id.tvExerciseSet)
        tvExerciseWeight=findViewById(R.id.tvExerciseWeight)
        btnDelete=findViewById(R.id.btnDelete)

        dbManager= recordDBManager(this,"exerciseRecord",null,1)
        sqlitedb=dbManager.writableDatabase

        var str_name=intent.getStringExtra("name")
        tvExerciseName.text=str_name

        var str_type=intent.getStringExtra("type")
        tvExerciseType.text=str_type

        var str_date=intent.getStringExtra("date")
        tvExerciseDate.text=str_date

        var str_count=intent.getStringExtra("count")
        tvExerciseCount.text=str_count

        var str_set=intent.getStringExtra("set")
        tvExerciseSet.text=str_set

        var str_weight=intent.getStringExtra("weight")
        tvExerciseWeight.text=str_weight

        btnDelete.setOnClickListener {
            sqlitedb.execSQL("DELETE FROM exerciseRecord WHERE ename='"+str_name+"'AND edate='"+str_date+ "';")
            sqlitedb.close()
            dbManager.close()

            finish()
        }


    }
}