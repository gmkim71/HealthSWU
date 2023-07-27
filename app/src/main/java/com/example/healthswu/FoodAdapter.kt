package com.example.healthswu

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(val context: Context, private val foodlist: ArrayList<FoodData>)
    :RecyclerView.Adapter<FoodAdapter.Holder>(){


    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_food1, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return foodlist.size
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(foodlist[position], context)
        holder.itemView.setOnClickListener {
            val str_name = foodlist.get(position).fname
            Toast.makeText(context, "${foodlist.get(position).fname} 레시피로 이동합니다.", Toast.LENGTH_SHORT).show()

            dbManager= DBManager(context,"foodDB",null,1)
            sqlitedb=dbManager.readableDatabase

            val cursor: Cursor
            cursor = sqlitedb.rawQuery("SELECT*FROM food WHERE name='"+str_name+"';",null)

            if(cursor.moveToNext()){
                val str_url = cursor.getString((cursor.getColumnIndex("url"))).toString()

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(str_url))
                context.startActivity(intent)
            }

            cursor.close()
            sqlitedb.close()
            dbManager.close()

        }
    }
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val foodPhoto = itemView?.findViewById<ImageView>(R.id.food_img)
        val foodName = itemView?.findViewById<TextView>(R.id.food_Name)
        val foodCal = itemView?.findViewById<TextView>(R.id.food_Cal)

        fun bind (foodData: FoodData, context: Context) {
            if (foodData.fimg != "") {
                val resourceId = context.resources.getIdentifier(foodData.fimg, "drawable", context.packageName)
                foodPhoto?.setImageResource(resourceId)
            } else {
                foodPhoto?.setImageResource(R.mipmap.ic_launcher)
            }
            /* 나머지 TextView와 String 데이터를 연결한다. */
            foodName?.text = foodData.fname
            foodCal?.text = foodData.fcal

        }
    }
}