package com.example.galgeleg.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.galgeleg.R
import com.example.galgeleg.model.Galgelogik
import com.example.galgeleg.model.Highscore
import com.example.galgeleg.view.adapter.HighscoreAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.fragment_winner.*

class WinnerFragment : Fragment() {

    val galgelogik = Galgelogik.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadHighScores()

        recyclerView_highScore.layoutManager = LinearLayoutManager(activity)
        recyclerView_highScore.adapter = HighscoreAdapter(galgelogik.highscores)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_winner, container, false)
    }

    private fun loadHighScores() {
        firestore.collection("highScores").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val highscore = document.toObject<Highscore>()
                    galgelogik.addHighScore(highscore)
                }
                recyclerView_highScore.adapter?.notifyDataSetChanged()
            }
            .addOnFailureListener {
                print("mistake")
            }
    }
}