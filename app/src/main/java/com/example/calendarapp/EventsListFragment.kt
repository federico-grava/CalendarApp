package com.example.calendarapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.databinding.FragmentEventsListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class EventsListFragment : Fragment(R.layout.fragment_events_list){
    private var dateFormat = SimpleDateFormat("dd/MM/yyyy")
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
            val placeholderList = mutableListOf(Event(0,"loading", "loading", "loading", "loading", "loading", "loading"))

            val adapter = MyAdapter(placeholderList, parentFragmentManager)
            myRecyclerView.adapter = adapter
            myRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)

            fetchEventsList(myRecyclerView, parentFragmentManager)

            fab.setOnClickListener {
                val fm = parentFragmentManager

                fm.commit {
                    setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out)
                    replace<NewEventFragment>(R.id.fragment_container_view)
                    setReorderingAllowed(true)
                    addToBackStack("EventsList")
                }
            }

        }
    }

    private fun fetchEventsList(rec: RecyclerView, fm : FragmentManager){
        GlobalScope.launch(Dispatchers.Main) {

            val db = DatabaseAndroid.getDatabase(requireContext())
            val dao = db.eventDao()

            val list = dao.getAll()
            if(list.isEmpty()){
                dao.insert(Event(0,"firstElement", "invisible", "0", "0", "0", "0"))
            }
            else{
                list.removeAt(0)
                list.sort()
            }
            rec.adapter = MyAdapter(list, fm)
        }
    }

}