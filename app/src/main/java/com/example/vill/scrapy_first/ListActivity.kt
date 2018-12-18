package com.example.vill.scrapy_first

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_list.*
import org.json.JSONObject

class ListActivity : AppCompatActivity() {
    private lateinit var json: JSONObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var intent = intent
        json = JSONObject(intent.getStringExtra("json"))
        var queryThread = QueryThread(json,handler)
        json = JSONObject()
        queryThread.start()
    }

    private   val handler: Handler = object : Handler(){
        override fun handleMessage(msg: Message?) {
            var length = json.length()
            var newJson = JSONObject(msg!!.obj.toString())
            for (i in 0 until newJson.length())
            {
                json.put("${length+i+1}","${newJson.getString("${length+i+1}")}")
            }
            json = JSONObject(msg!!.obj.toString())
            //Toast.makeText(this@ListActivity,"json is $json",Toast.LENGTH_LONG).show()
            var myListAdapter = MyListAdapter(json,this@ListActivity)
            result_list_view.adapter = myListAdapter
        }
    }
}
