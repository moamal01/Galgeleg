package com.example.galgeleg

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.highscore_row.view.*

class HighscoreAdapter : RecyclerView.Adapter<ViewHolder>() {

    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var scores = mutableListOf<Highscore>()

    init {
        var h: Highscore = Highscore("Moamal", 0, "Abekat")
        var h1: Highscore = Highscore("Moamal", 0, "Abekat")
        var h2: Highscore = Highscore("Moamal", 0, "Abekat")
        var h3: Highscore = Highscore("Moamal", 0, "Abekat")
        var h4: Highscore = Highscore("Moamal", 0, "Abekat")

        scores.add(0, h)
        scores.add(0, h)
        scores.add(0, h)
        scores.add(0, h)
        scores.add(0, h)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val highscoreRow = layoutInflater.inflate(R.layout.highscore_row, parent, false)
        return ViewHolder(highscoreRow)
    }

    override fun getItemCount(): Int {
        return scores.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.name_highScore.text = scores[position].player
        holder.view.tries_highScore.text = scores[position].score.toString()
        holder.view.word_highScore.text = scores[position].word
    }
}