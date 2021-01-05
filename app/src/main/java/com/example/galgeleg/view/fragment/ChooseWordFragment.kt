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
import com.example.galgeleg.view.activity.GameActivity
import com.example.galgeleg.view.activity.MainActivity
import com.example.galgeleg.view.adapter.ChooseWordAdapter
import kotlinx.android.synthetic.main.fragment_choose_word.*
import kotlinx.android.synthetic.main.word_row.*

class ChooseWordFragment() : Fragment(), View.OnClickListener {

    var galgelogik = Galgelogik.getInstance()
    lateinit var word: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_word, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        start_button_choose.setOnClickListener(this)

        recyclerView_choose_word.layoutManager = LinearLayoutManager(activity)
        recyclerView_choose_word.adapter = ChooseWordAdapter(galgelogik.muligeOrd)
    }

    override fun onClick(p0: View?) {
        word = word_choose.text.toString()

        val intent = Intent(activity, GameActivity::class.java)
        startActivity(intent)
        galgelogik.startNytSpil(word)
    }
}