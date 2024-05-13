package com.mjolnir.todolist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.recyclerview.widget.RecyclerView

class Adapter(var myData:List<Mydata>):RecyclerView.Adapter<Adapter.viewHolder>(){
    class viewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var title=itemView.findViewById<TextView>(R.id.tv_title)
        var priority=itemView.findViewById<TextView>(R.id.tv_priority)
        var mylayout=itemView.findViewById<LinearLayout>(R.id.ll_mylayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var itemView=LayoutInflater.from(parent.context).inflate(R.layout.each_row,parent,false)
        return viewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        when(myData[position].priority.toLowerCase().trim()){
            "high"-> holder.mylayout.setBackgroundResource(R.drawable.bg_priority_high)
            "medium"->holder.mylayout.setBackgroundResource(R.drawable.bg_priority_medium)
            else-> holder.mylayout.setBackgroundResource(R.drawable.bg_priority_low)
        }

        holder.title.text=myData[position].title
        holder.priority.text=myData[position].priority
        holder.itemView.setOnClickListener {
            val intent=Intent(holder.itemView.context,update_task::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }
    }

}