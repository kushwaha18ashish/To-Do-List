package com.mjolnir.todolist

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.mjolnir.todolist.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var database: myDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database= Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To_Do"
        ).build()

        binding.addItem.setOnClickListener {
            val intent=Intent(this,create_task::class.java)
            startActivity(intent)
        }

        binding.deleteAllItem.setOnClickListener {

            val builder1= AlertDialog.Builder(this)
            builder1.setTitle("Do you want to delete all tasks?")
            builder1.setMessage("All tasks will be deleted permanently.Do you want to delete all tasks?")
            builder1.setIcon(R.drawable.baseline_delete_24)
            builder1.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                DataObject.deleteAll()
                GlobalScope.launch {
                    database.dao().deleteAll()
                }
                setRecyclerView()
            })
            builder1.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

            })
            builder1.show()

        }

        setRecyclerView()

    }
    fun setRecyclerView(){
        binding.rvRecyclerView.adapter=Adapter(DataObject.getAllData())
        binding.rvRecyclerView.layoutManager=LinearLayoutManager(this)
    }
}