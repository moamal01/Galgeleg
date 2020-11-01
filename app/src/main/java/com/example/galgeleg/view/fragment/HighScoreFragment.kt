package com.example.galgeleg.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.galgeleg.R
import com.example.galgeleg.controller.FirebaseController
import com.example.galgeleg.controller.Galgelogik
import com.example.galgeleg.view.adapter.HighscoreAdapter
import kotlinx.android.synthetic.main.fragment_highscore.*

class HighScoreFragment : Fragment() {
    val galgelogik = Galgelogik.getInstance()
    val firebaseController = FirebaseController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseController.clearHighScores()
        firebaseController.loadHighScores(recyclerView_highScoreFragment)

        recyclerView_highScoreFragment.layoutManager = LinearLayoutManager(activity)
        recyclerView_highScoreFragment.adapter = HighscoreAdapter(galgelogik.highscores)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_highscore, container, false)
    }
}