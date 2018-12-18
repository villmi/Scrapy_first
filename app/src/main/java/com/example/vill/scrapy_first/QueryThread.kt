package com.example.vill.scrapy_first

import android.os.Handler
import android.os.Message
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class QueryThread(json : JSONObject,handler: Handler) : Thread() {
    private var json = json
    private var handler = handler
    override fun run() {
        val socket = Socket("139.199.208.222",2333)
        var outputStream = socket.getOutputStream()
        var printWriter = PrintWriter(outputStream)
        printWriter.write(json.toString())
        printWriter.write("\n")
        printWriter.flush()
        var inputStream = socket.getInputStream()
        var inputStreamReader = InputStreamReader(inputStream)
        var bufferedReader = BufferedReader(inputStreamReader)
        var response = bufferedReader.readLine()
        json = JSONObject(response)
        if(json.getString("head")=="villhahaha")
        {
            var description = json.getString("description")
            when(description)
            {
                "begin" -> {
                    getAll(bufferedReader,handler)
                }
                "2" -> {

                }
                else -> {

                }
            }
        }
    }

    private fun getAll(bufferedReader: BufferedReader, handler: Handler)
    {
        var flag = true
        while (flag)
        {
            var result = bufferedReader.readLine()
            var json = JSONObject(result)
            var msg = Message()
            if (json.getString("head") == "villhahaha")
                if (json.getString("description") == "scrapy_result")
                {
                    val endFlag = json.getString("end")
                    json = JSONObject(json.getString("result"))
                    msg.obj = json
                    handler.sendMessage(msg)
                    if (endFlag == "end")
                        flag = false
                }
        }
    }
}