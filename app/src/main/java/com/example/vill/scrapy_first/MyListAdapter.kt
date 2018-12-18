package com.example.vill.scrapy_first

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject


class MyListAdapter(json: JSONObject, context: Context): BaseAdapter() {
    override fun getItem(position: Int): Any {
        return JSONArray(json.getString("${position+1}"))
    }

    private var layoutInflate: LayoutInflater = LayoutInflater.from(context)
    private var json = json

    @SuppressLint("ResourceType")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder? = ViewHolder()
        var view: View? = convertView
        if(view == null)
        {
            view = layoutInflate.inflate(R.layout.list_view_item,null)

            viewHolder?.itemTitle = view.findViewById(R.id.item_title)
            viewHolder?.itemChannel = view.findViewById(R.id.item_channel)
            viewHolder?.itemDate = view.findViewById(R.id.item_date)
            viewHolder?.itemLocaton = view.findViewById(R.id.item_location)

            view.tag = viewHolder
            println(position)
        }
        else
            viewHolder = view.tag as ViewHolder
        viewHolder?.itemTitle?.text = JSONArray(getItem(position).toString()).getString(0)
        viewHolder?.itemLocaton?.text = JSONArray(getItem(position).toString()).getString(2)
        viewHolder?.itemChannel?.text = JSONArray(getItem(position).toString()).getString(3)
        viewHolder?.itemDate?.text = JSONArray(getItem(position).toString()).getString(4)

        return view!!
    }


    override fun getItemId(position: Int): Long {
        println("position:$position")
        return position.toLong()
    }

    override fun getCount(): Int {
        println("count:${json.length()}")
        return json.length()
    }

    private class ViewHolder
    {
        lateinit var itemTitle:TextView
        lateinit var itemChannel:TextView
        lateinit var itemDate:TextView
        lateinit var itemLocaton:TextView

    }

}