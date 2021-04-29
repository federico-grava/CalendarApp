package com.example.calendarapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.databinding.FragmentEventsListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EventsListFragment : Fragment(R.layout.fragment_events_list){
    private var dateFormat = SimpleDateFormat("dd-MM-yyyy")
    private var timeFormat = SimpleDateFormat("kk:mm")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEventsListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateFormat.isLenient = false
        timeFormat.isLenient = false
        val binding = FragmentEventsListBinding.bind(view)

        with(binding) {
            //testo.text="hey"

            val placeholderList = mutableListOf(Event(0,"loading", "loading", "loading", "loading", "loading", "loading"))

            val adapter = MyAdapter(placeholderList)
            myRecyclerView.adapter = adapter
            myRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)

            fetchEventsList(adapter)

            buttset.setOnClickListener {
                //myRecyclerView.adapter = MyAdapter(listOf(Event(1,"titolo1", "ciao1", "dateFo", "er", "df", "sdfd")))

                /*val s = testo.text.toString()
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
                }*/


            }

            buttget.setOnClickListener {
                val fm = parentFragmentManager

                fm.commit {
                    setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out)
                    replace<NewEventFragment>(R.id.fragment_container_view)
                    setReorderingAllowed(true)
                    addToBackStack("EventsList") // name can be null
                }
            }

        }
    }

    private fun fetchEventsList(adapter: MyAdapter){
        GlobalScope.launch(Dispatchers.Main) {

            val db = DatabaseAndroid.getDatabase(requireContext())
            val dao = db.eventDao()

            val list = dao.getAll()
            if(list.isEmpty()){
                dao.insert(Event(0,"firstElement", "invisible", "0", "0", "0", "0"))
            }
            else{
                list.removeAt(0)
            }
            adapter.mData = list
            try{adapter.notifyDataSetChanged()} //Cannot call this method while RecyclerView is computing a layout or scrolling
            catch (e:Exception){}               //this doesn't work
        }

        //To do: get events from database, create list from today (or first event) to last event
        /*
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
    */
    }

}