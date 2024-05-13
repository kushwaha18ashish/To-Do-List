package com.mjolnir.todolist

import android.icu.text.CaseMap.Title

object DataObject {
    var listData= mutableListOf<Mydata>()

    fun setData(title: String,priority:String){
        listData.add(Mydata(title,priority))
    }

    fun getAllData():List<Mydata>{
        return listData
    }

    fun deleteAll(){
        listData.clear()
    }

    fun getData(pos:Int):Mydata{
        return listData[pos]
    }

    fun deleteData(pos:Int){
        listData.removeAt(pos)
    }

    fun updateData(pos:Int,title:String,priority:String){
        listData[pos].title=title
        listData[pos].priority=priority
    }
}