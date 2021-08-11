package com.henriquesbraga.recycleswipeexample.view.adapter


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.henriquesbraga.recycleswipeexample.R

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var card:       MaterialCardView = itemView.findViewById(R.id.card)
    var name:       TextView = itemView.findViewById(R.id.name)
    var subname:    TextView = itemView.findViewById(R.id.subname)
    var ocupation:  TextView = itemView.findViewById(R.id.ocupation)

    /*
    fun bind(person: Person){
        name.text       = person.name
        subname.text    = person.subname
        ocupation.text  = person.ocupation
        card.setOnLongClickListener {
            Log.d("test", "Mensagem")
            true
        }
    }*/
}