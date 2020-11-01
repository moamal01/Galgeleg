package com.example.galgeleg.controller

import android.content.ContentValues.TAG
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.galgeleg.model.Highscore
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.android.synthetic.main.fragment_winner.*

class FirebaseController {

    val galgelogik = Galgelogik.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun loadHighScores(recyclerView: RecyclerView) {
        firestore.collection("highscores").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val highscore = document.toObject<Highscore>()
                    galgelogik.addHighScore(highscore)
                }
                recyclerView.adapter?.notifyDataSetChanged()
            }
            .addOnFailureListener {
                print("mistake")
            }
    }

    fun submitScore(name: String) {

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

    fun clearHighScores() {
        galgelogik.highscores.clear()
    }
}