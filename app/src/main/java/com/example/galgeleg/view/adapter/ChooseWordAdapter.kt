package com.example.galgeleg.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.galgeleg.R
import com.example.galgeleg.controller.Galgelogik
import kotlinx.android.synthetic.main.highscore_row.view.*
import kotlinx.android.synthetic.main.word_row.view.*

class ChooseWordAdapter(private val words: ArrayList<String>,
private val listener: (String, Int) -> Unit): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val wordRow = layoutInflater.inflate(R.layout.word_row, parent, false)
        return ViewHolder(wordRow)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.word_choose.text = words[position]

        val string = words[position]
        holder.itemView.setOnClickListener { listener(string, position)}
    }
}