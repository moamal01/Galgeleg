package com.example.galgeleg.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.galgeleg.R
import com.example.galgeleg.model.Galgelogik
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class GameActivity : AppCompatActivity(), View.OnClickListener {
    val galgelogik: Galgelogik = Galgelogik()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sæt onClickListener
        gæt_button.setOnClickListener(this)

        galgelogik.startNytSpil()
        word_textView.text = galgelogik.synligtOrd
    }

    override fun onClick(view: View?) {
        val bogstav: String = letter_guess.text.toString().toLowerCase(Locale.ROOT)

        galgelogik.gætBogstav(bogstav)
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

            fragmentTransaction.replace(R.id.frame_game, winnerFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        } else if (galgelogik.erSpilletTabt()) {
            val loserFragment = LoserFragment()

            fragmentTransaction.replace(R.id.frame_game, loserFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

//    private fun load() {
//        firestore.collection("inventory").get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    val grocery = document.toObject<Grocery>()
//                    restaurant.addGrocery(grocery)
//                }
//                recyclerview_inventory.adapter?.notifyDataSetChanged()
//            }
//            .addOnFailureListener {
//                print("mistake")
//            }
//    }
}