package com.example.galgeleg.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.galgeleg.R
import com.example.galgeleg.controller.FirebaseController
import com.example.galgeleg.controller.Galgelogik
import com.example.galgeleg.view.fragment.LoserFragment
import com.example.galgeleg.view.fragment.WinnerFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class GameActivity : AppCompatActivity(), View.OnClickListener {

    val galgelogik = Galgelogik.getInstance()
    val firebaseController = FirebaseController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        galgelogik.enterPressed(letter_guess, guess_button)

        guess_button.setOnClickListener(this)

        word_textView.text = galgelogik.synligtOrd
    }

    override fun onClick(view: View?) {
        val letter: String = letter_guess.text.toString().toLowerCase(Locale.ROOT)

        galgelogik.gætBogstav(letter)
        gættede_bogstav_textView.text = galgelogik.brugteBogstaver.toString()

        word_textView.text = galgelogik.synligtOrd
        letter_guess.text = null

        updateHangman()
        onEndGame()
    }

    private fun updateHangman() {
        when (galgelogik.antalForkerteBogstaver) {
            1 -> hangman_imageView.setImageResource(R.drawable.forkert1)
            2 -> hangman_imageView.setImageResource(R.drawable.forkert2)
            3 -> hangman_imageView.setImageResource(R.drawable.forkert3)
            4 -> hangman_imageView.setImageResource(R.drawable.forkert4)
            5 -> hangman_imageView.setImageResource(R.drawable.forkert5)
            6 -> hangman_imageView.setImageResource(R.drawable.forkert6)
        }
    }

    private fun onEndGame() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        if (galgelogik.erSpilletVundet()) {
            val name = intent.getStringExtra("name")
            firebaseController.submitScore(name!!)

            val winnerFragment = WinnerFragment()

            fragmentTransaction.replace(R.id.frame_game, winnerFragment)
            fragmentTransaction.commit()
        } else if (galgelogik.erSpilletTabt()) {
            val loserFragment = LoserFragment()

            fragmentTransaction.replace(R.id.frame_game, loserFragment)
            fragmentTransaction.commit()
        }
    }

}