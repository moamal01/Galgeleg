package com.example.galgeleg.view.activity

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import java.util.concurrent.Executors

class GameActivity : AppCompatActivity(), View.OnClickListener {
    private val bgThread = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())
    private val TAG = "GameActivity"

    val galgelogik: Galgelogik = Galgelogik()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sæt onClickListener
        gæt_button.setOnClickListener(this)

//        DoAsync {
//            handler.post {
//                galgelogik.hentOrdFraDr()
//            }
//        }.execute()
        bgThread.execute {
            galgelogik.hentOrdFraDr()
        }

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

            fragmentTransaction.replace(R.id.frame_game, winnerFragment)
            fragmentTransaction.commit()
        } else if (galgelogik.erSpilletTabt()) {
            val loserFragment = LoserFragment()

            submitScore()
            fragmentTransaction.replace(R.id.frame_game, loserFragment)
            fragmentTransaction.commit()
        }
    }

    class DoAsync(val handler:() -> Unit) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg p0: Void?): Void? {
            handler()
            return null
        }
    }

    private fun submitScore() {
        // Add a new document with a generated id.
        val data = hashMapOf(
            "name" to "Tokyo",
            "country" to "Japan"
        )

        firestore.collection("cities")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}