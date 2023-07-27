package com.example.healthswu

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "Userdata", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Userdata (username TEXT PRIMARY KEY, password TEXT, sex TEXT, height REAL, weight REAL, goal TEXT, details TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Userdata")
        onCreate(db)
    }

    fun insertdata(username: String?, password: String?, sex: String?, height: Double?, weight: Double?, goal: String?, details: String?): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("password", password)
        sex?.let {cv.put("sex", it)} // null이 아닌 경우에만 값 추가
        height?.let { cv.put("height", it) } // null이 아닌 경우에만 값 추가
        weight?.let { cv.put("weight", it) } // null이 아닌 경우에만 값 추가
        goal?.let { cv.put("goal", it) } // null이 아닌 경우에만 값 추가
        details?.let { cv.put("details", it) } // null이 아닌 경우에만 값 추가
        val result = db.insert("Userdata", null, cv)
        db.close()
        return result != -1L
    }

    fun checkuserpass(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM Userdata WHERE username='$username' AND password='$password'"
        val cursor = db.rawQuery(query, null)
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }
}
