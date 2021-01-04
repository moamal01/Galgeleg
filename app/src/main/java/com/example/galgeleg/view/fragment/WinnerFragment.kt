package com.example.galgeleg.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.galgeleg.R
import com.example.galgeleg.controller.FirebaseController
import com.example.galgeleg.controller.Galgelogik
import com.example.galgeleg.view.activity.MainActivity
import com.example.galgeleg.view.adapter.HighscoreAdapter
import kotlinx.android.synthetic.main.fragment_winner.*

class WinnerFragment : Fragment(), View.OnClickListener {

    val galgelogik = Galgelogik.getInstance()
    val firebaseController = FirebaseController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        main_menu_winner.setOnClickListener(this)

        firebaseController.clearHighScores()
        firebaseController.loadHighScores(recyclerView_highScore)

        recyclerView_highScore.layoutManager = LinearLayoutManager(activity)
        recyclerView_highScore.adapter = HighscoreAdapter(galgelogik.highscores)

        tryCount_text.text = galgelogik.antalForkerteBogstaver.toString() + " forkerte"
        word_reveal_winner.text = "Ordet var: " + galgelogik.ordet
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_winner, container, false)
    }

    override fun onClick(p0: View?) {
        activity?.finish()
        val intent = Intent(activity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}