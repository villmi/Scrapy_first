package com.example.vill.scrapy_first

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var activityView : View
    private lateinit var json:JSONObject

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityView = View(this)

        json = JSONObject()
        search_btn.setOnClickListener {
            var searchText = search_edit.text.toString()
            var words = searchText.split(" ")
            var json = JSONObject()
            json.put("head","villhahahaha")
            var main = JSONObject()
            for((count, wrod) in words.withIndex())
            {
                var i = count + 1
                main.put("$i","$wrod")
            }
            json.put("keywords",main)
            json.put("count",200)
            var intent = Intent(this@MainActivity,ListActivity::class.java)
            intent.putExtra("json",json.toString())
            //Toast.makeText(this@MainActivity,"Query Start",Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

    }

}
