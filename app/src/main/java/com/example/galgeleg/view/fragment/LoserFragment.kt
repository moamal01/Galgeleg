package com.example.galgeleg.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.galgeleg.R
import com.example.galgeleg.controller.Galgelogik
import com.example.galgeleg.view.activity.MainActivity
import com.example.galgeleg.view.adapter.HighscoreAdapter
import kotlinx.android.synthetic.main.fragment_loser.*
import kotlinx.android.synthetic.main.fragment_winner.*

class LoserFragment : Fragment(), View.OnClickListener {

    val galgelogik = Galgelogik.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        word_reveal_loser.text = galgelogik.ordet
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loser, container, false)
    }

    override fun onClick(p0: View?) {
        activity?.finish()
        val intent = Intent(activity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}