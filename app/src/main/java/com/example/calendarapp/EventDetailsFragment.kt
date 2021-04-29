package com.example.calendarapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calendarapp.databinding.FragmentEventDetailsBinding

class EventDetailsFragment(var titlePar:String?, var startPar:String, var endPar:String, var notePar:String?) : Fragment(R.layout.fragment_new_event){
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentEventDetailsBinding.bind(view)

        with(binding){
            title.text = titlePar
            dateStart.text = startPar
            dateEnd.text = endPar
            note.text = notePar
        }
    }
}