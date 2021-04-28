package com.example.calendarapp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendarapp.databinding.FragmentEventsListBinding
import com.example.calendarapp.databinding.FragmentNewEventBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class EventsListFragment : Fragment(R.layout.fragment_events_list){
    private var dateFormat = SimpleDateFormat("dd-MM-yyyy")
    private var timeFormat = SimpleDateFormat("hh:mm")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventsListBinding.inflate(layoutInflater)

        val arr = createEventsList()

        with(binding) {
            //testo.text="hey"
            myRecyclerView.adapter = MyAdapter(arr)
            myRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)

            buttset.setOnClickListener {

                val s = testo.text.toString()
                try{val useless = dateFormat.parse(s)}
                catch(e: ParseException){
                    Toast.makeText(requireContext(), "Invalid date",
                        Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                GlobalScope.launch {

                    val db = DatabaseAndroid.getDatabase(requireContext())
                    val dao = db.eventDao()

                    dao.insert(Event(2,"pulsanteClick", "forse", s, "12-12-2021", "13:56", "14:30"))
                }


            }

            buttget.setOnClickListener {

            }


        }

        return binding.root
}
    private fun createEventsList() : ArrayList<Event>{


        //To do: get events from database, create list from today (or first event) to last event
        val e1 = Calendar.getInstance()
        val e2 = Calendar.getInstance()
        val e3 = Calendar.getInstance()
        val e4 = Calendar.getInstance()
        e1.set(2021, 3, 28, 2, 5)
        e2.set(2021, 5, 13, 21, 45)
        e3.set(2021, 5, 13, 21, 45)
        e4.set(2021, 12, 13, 21, 45)

        return arrayListOf(Event(1,"titolo1", "ciao1", dateFormat.format(e1.time), dateFormat.format(e2.time), timeFormat.format(e1.time), timeFormat.format(e2.time)),
            Event(1,"titolo1", "ciao1", dateFormat.format(e3.time), dateFormat.format(e4.time), timeFormat.format(e2.time), timeFormat.format(e4.time)))
    }

}