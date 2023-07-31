package com.example.healthswu

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class RecordActivity : AppCompatActivity() {
    lateinit var dbManager: recordDBManager
    lateinit var sqlitedb : SQLiteDatabase

    lateinit var edtExerciseName :EditText
    lateinit var spinExerciseType : Spinner
    lateinit var dateExercise : DatePicker
    lateinit var edtExerciseCount : EditText
    lateinit var edtExerciseSet : EditText
    lateinit var edtExerciseWeight : EditText
    lateinit var btnSave : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        edtExerciseName =findViewById(R.id.edtExerciseName)
        spinExerciseType=findViewById(R.id.spinExerciseType)
        dateExercise =findViewById(R.id.dateExercise)
         edtExerciseCount =findViewById(R.id.edtExerciseCount)
        edtExerciseSet =findViewById(R.id.edtExerciseSet)
        edtExerciseWeight =findViewById(R.id.edtExerciseWeight)
        btnSave =findViewById(R.id.btnSave)

        spinExerciseType.adapter=ArrayAdapter.createFromResource(this, R.array.itemType, android.R.layout.simple_spinner_dropdown_item)

        dbManager= recordDBManager(this, "exerciseRecord",null,1)

        // 저장 버튼 클릭 시
        btnSave.setOnClickListener {

            var str_name : String=edtExerciseName.text.toString()
            var str_type : String=spinExerciseType.selectedItem.toString()
            var str_date="${dateExercise.year}/${dateExercise.month+1}/${dateExercise.dayOfMonth}"
            var int_count  = edtExerciseCount.text
            var int_set=edtExerciseSet.text
            var int_weight=edtExerciseWeight.text


            sqlitedb=dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO exerciseRecord VALUES ('"+str_name+"', '"
                    +str_type+"','"+str_date+"', "+int_count+", "+int_set+", "+int_weight+");")
            sqlitedb.close()



            finish()   // 액티비티 종료
        }
    }
}