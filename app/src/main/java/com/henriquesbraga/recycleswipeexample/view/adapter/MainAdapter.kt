package com.henriquesbraga.recycleswipeexample.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.henriquesbraga.recycleswipeexample.R
import com.henriquesbraga.recycleswipeexample.model.Person

class MainAdapter(context: Context, view: View): RecyclerView.Adapter<MainViewHolder>() {

    private var dataList: ArrayList<Person> = arrayListOf()
    private var archievedList: ArrayList<Person> = arrayListOf()
    var context = context
    var view = view

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.name.text = dataList[position].name
        holder.subname.text = dataList[position].subname
        holder.ocupation.text = dataList[position].ocupation
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    fun setData(list: ArrayList<Person>){
        dataList = list
    }

    fun deleteData(position: Int){
        var obj = dataList[position]
        dataList.removeAt(position)
        notifyItemRemoved(position)
        Snackbar.make(view, obj.name, Snackbar.LENGTH_LONG).setAction("Undo") {
            dataList.add(position, obj)
            notifyItemInserted(position)
        }.show()
    }

    fun archieveData(position: Int){
        var obj = dataList[position]
        archievedList.add(obj)
        dataList.removeAt(position)
        notifyItemRemoved(position)
        Snackbar.make(view, obj.name + "Archieved!", Snackbar.LENGTH_LONG).setAction("Undo") {
            archievedList.removeAt(archievedList.lastIndexOf(obj))
            dataList.add(position, obj)
            notifyItemInserted(position)
        }.show()
    }

}