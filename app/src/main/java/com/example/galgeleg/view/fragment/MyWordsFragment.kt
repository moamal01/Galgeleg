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
import com.example.galgeleg.view.adapter.ChooseWordAdapter
import com.example.galgeleg.view.adapter.MyWordsAdapter
import kotlinx.android.synthetic.main.fragment_my_words.*

class MyWordsFragment(var name: String) : Fragment(), View.OnClickListener {

    val galgelogik = Galgelogik.getInstance()
    lateinit var word: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_words, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        save_word_button.setOnClickListener(this)

        recyclerView_my_words.layoutManager = LinearLayoutManager(activity)
        recyclerView_my_words.adapter = ChooseWordAdapter(galgelogik.loadWord(activity)) { string, pos -> wordChosen(string, pos) }
    }

    private fun openActivity() {
        val intent = Intent(activity, GameActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
    }

    private fun wordChosen(string: String, pos: Int) {
        galgelogik.startNytSpil(string)
        openActivity()
    }

    override fun onClick(p0: View?) {
        word = write_my_word.text.toString()
        if (word != "") {
            galgelogik.saveWord(activity, word)
            recyclerView_my_words.adapter?.notifyDataSetChanged()
        }
    }
}