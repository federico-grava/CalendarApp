package com.example.calendarapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.calendarapp.databinding.FragmentEventDetailsBinding
import com.example.calendarapp.databinding.FragmentNewEventBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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