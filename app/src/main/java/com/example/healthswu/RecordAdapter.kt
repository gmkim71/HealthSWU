package com.example.test

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RecordAdapter(private val context : Context) : RecyclerView.Adapter <RecordAdapter.ViewHolder>() {

    var records= mutableListOf<RecordData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.record_recycler,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(records[position])
    }

    override fun getItemCount(): Int {
        return records.size
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view!!) {

        private var chkComplete = itemView.findViewById<CheckBox>(R.id.chkComplete)
        private var tvName : TextView = itemView.findViewById<TextView>(R.id.textExercise)
        private var tvDatail=itemView.findViewById<TextView>(R.id.textDetail)
        private var recyclerLayout = itemView.findViewById<ConstraintLayout>(R.id.recyclerLayout)
        private var btnDetail=itemView.findViewById<Button>(R.id.btnInfo)

        fun bind(item:RecordData) {

            chkComplete.setOnCheckedChangeListener { button, isChecked ->
                if(isChecked)
                    recyclerLayout.setBackgroundColor(Color.parseColor("#E8DFFF"))
                else
                    recyclerLayout.setBackgroundColor(Color.WHITE)
            }
            tvName.text=item.name.toString()
            tvDatail.text=item.detail.toString()

            var date=item.date.toString()

            btnDetail.setOnClickListener {

                val intent = Intent(context, RecordInfo::class.java)
                intent.putExtra("name",item.name.toString())
                intent.putExtra("type",item.type.toString())
                intent.putExtra("date",date)
                intent.putExtra("count",item.count)
                intent.putExtra("set",item.set)
                intent.putExtra("weight",item.weight)

                startActivity(context,intent,null)
            }
        }
    }

}

