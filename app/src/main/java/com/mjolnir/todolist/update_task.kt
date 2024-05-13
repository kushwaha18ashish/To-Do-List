package com.mjolnir.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class update_task : AppCompatActivity() {
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_task)

        var titleUpdate=findViewById<EditText>(R.id.et_title_update)
        var priorityUpdate=findViewById<EditText>(R.id.et_priority_update)
        var btnSave=findViewById<Button>(R.id.btn_save_update)
        var btnDelete=findViewById<Button>(R.id.btn_delete_update)

        database= Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To_Do"
        ).build()

        val pos=intent.getIntExtra("id",-1)
        if(pos!=-1){
            val title=DataObject.getData(pos).title
            val priority=DataObject.getData(pos).priority

            titleUpdate.setText(title)
            priorityUpdate.setText(priority)

            btnDelete.setOnClickListener {
                DataObject.deleteData(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(Entity(pos+1,title, priority))

                }
                myIntent()
            }

            btnSave.setOnClickListener {
                DataObject.updateData(pos,titleUpdate.text.toString(),
                    priorityUpdate.text.toString())
                GlobalScope.launch {
                    database.dao().updateTask(Entity(pos+1,titleUpdate.text.toString(), priorityUpdate.text.toString()))
                }
                myIntent()
            }

        }
    }

    fun myIntent(){
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}