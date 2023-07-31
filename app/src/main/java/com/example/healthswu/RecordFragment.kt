package com.example.healthswu

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthswu.R
import com.example.healthswu.RecordActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class RecordFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var recycleRecord : RecyclerView
    lateinit var dbManager: recordDBManager
    lateinit var sqlitedb : SQLiteDatabase

    val records= mutableListOf<RecordData>()

    lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarView=view.findViewById(R.id.calendarView)
        recycleRecord= view.findViewById(R.id.recyclerView)

        // 캘린더뷰 날짜 변경 시 해당되는 운동계획을 리사이클러뷰에 보여줌
        calendarView.setOnDateChangeListener { view, year, month, dayOfmonth ->
            var calendarDate = "${year}/${month+1}/${dayOfmonth}"

            dbManager= recordDBManager(context,"exerciseRecord",null,1)
            sqlitedb=dbManager.readableDatabase

            val cursor : Cursor
            cursor=sqlitedb.rawQuery("SELECT * FROM exerciseRecord WHERE edate ='"+calendarDate+"';",null)

            // 리사이클러 레이아웃
            recycleRecord.removeAllViewsInLayout()
            records.clear()

            var recordAdapter = RecordAdapter(requireContext())
            recycleRecord.adapter=recordAdapter

            while(cursor.moveToNext()) {
                val str_name=cursor.getString(0).toString()
                val str_type=cursor.getString(1).toString()
                val str_date=cursor.getString(2).toString()
                var str_count=cursor.getString(3).toString()
                val str_set=cursor.getString(4).toString()
                val str_weight=cursor.getString(5).toString()
                val str_detail=str_count+ "회 X "+ str_set + "세트"
                records.add(RecordData(name=str_name, type=str_type,date=str_date,count=str_count, set=str_set, weight=str_weight, detail=str_detail))
                recordAdapter.records=records
                recordAdapter.notifyDataSetChanged()
            }
            cursor.close()
            dbManager.close()
            sqlitedb.close()
        }


        // 플로팅액션버튼 : 클릭 시 운동 기록 추가 액티비티(RecordActivity)로 전환
        var fabAdd :FloatingActionButton=view.findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {
            val intent = Intent(context, RecordActivity::class.java)
            startActivity(intent)
        }



    }
    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecordFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
