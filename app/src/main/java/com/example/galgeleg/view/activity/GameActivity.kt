package com.example.galgeleg.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.example.galgeleg.R
import com.example.galgeleg.model.Galgelogik
import com.example.galgeleg.view.fragment.LoserFragment
import com.example.galgeleg.view.fragment.WinnerFragment
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class GameActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "GameActivity"

    val galgelogik = Galgelogik.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
//    val name = intent.extras

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sæt onClickListener
        gæt_button.setOnClickListener(this)

        word_textView.text = galgelogik.synligtOrd
    }

    override fun onClick(view: View?) {
        val letter: String = letter_guess.text.toString().toLowerCase(Locale.ROOT)

        galgelogik.gætBogstav(letter)
        gættede_bogstav_textView.text = galgelogik.brugteBogstaver.toString()

        word_textView.text = galgelogik.synligtOrd

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
            val winnerFragment = WinnerFragment()
            submitScore()

            fragmentTransaction.replace(R.id.frame_game, winnerFragment)
            fragmentTransaction.commit()
        } else if (galgelogik.erSpilletTabt()) {
            val loserFragment = LoserFragment()

            fragmentTransaction.replace(R.id.frame_game, loserFragment)
            fragmentTransaction.commit()
        }
    }

    private fun submitScore() {
        val name = intent.getStringExtra("name")

        val data = hashMapOf(
            "name" to name,
            "tries" to galgelogik.antalForkerteBogstaver,
            "word" to galgelogik.ordet
        )

        firestore.collection("highscores")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}