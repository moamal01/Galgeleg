package com.example.galgeleg.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.galgeleg.R
import com.example.galgeleg.controller.Galgelogik
import com.example.galgeleg.view.adapter.ChooseWordAdapter
import kotlinx.android.synthetic.main.fragment_choose_word.*

class ChooseWordFragment() : Fragment() {

    var galgelogik = Galgelogik.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_word, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_choose_word.layoutManager = LinearLayoutManager(activity)
        recyclerView_choose_word.adapter = ChooseWordAdapter(galgelogik.muligeOrd)
    }
}