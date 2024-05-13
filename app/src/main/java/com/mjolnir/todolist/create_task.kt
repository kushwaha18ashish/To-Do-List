package com.mjolnir.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class create_task : AppCompatActivity() {

    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_task)

        var titleCreate=findViewById<EditText>(R.id.et_title_create)
        var priorityCreate=findViewById<EditText>(R.id.et_priority_create)
        var btnSave=findViewById<Button>(R.id.btn_save_create)


        //error yhi se aa rha
        database=Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To_Do"
        ).build()


        btnSave.setOnClickListener {
            if(titleCreate.text.toString().trim { it<=' ' }.isNotEmpty()
                && priorityCreate.text.toString().trim { it<=' ' }.isNotEmpty()){
                var title=titleCreate.text.toString()
                var priority=priorityCreate.text.toString()
                DataObject.setData(title,priority)

                GlobalScope.launch {
                    database.dao().insertTask(Entity(0,title,priority))
                }


                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}