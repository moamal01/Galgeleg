package com.example.galgeleg.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.galgeleg.R
import com.example.galgeleg.model.Highscore
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.highscore_row.view.*

class HighscoreAdapter(private val highscores: List<Highscore>) : RecyclerView.Adapter<ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val highscoreRow = layoutInflater.inflate(R.layout.highscore_row, parent, false)
        return ViewHolder(highscoreRow)
    }

    override fun getItemCount(): Int {
        return highscores.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.name_highScore.text = highscores[position].player
        holder.view.tries_highScore.text = highscores[position].score.toString()
        holder.view.word_highScore.text = highscores[position].word
    }
}