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
import com.example.calendarapp.databinding.FragmentNewEventBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class NewEventFragment : Fragment(R.layout.fragment_new_event){
    private var dateFormat = SimpleDateFormat("dd-MM-yyyy")
    private var timeFormat = SimpleDateFormat("hh:mm")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNewEventBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateFormat.isLenient = false
        timeFormat.isLenient = false
        val binding = FragmentNewEventBinding.bind(view)

        with(binding){

            dateStart.addTextChangedListener(object:TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
                override fun afterTextChanged(s: Editable?) {
                    dateEnd.text = dateStart.text
                }
            })

            timeStart.addTextChangedListener(object:TextWatcher{
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
                override fun afterTextChanged(s: Editable?) {
                    timeEnd.text = timeStart.text
                }
            })


            buttonAdd.setOnClickListener {

                var title = title.text.toString()
                val note = note.text.toString()
                val dateStart = dateStart.text.toString()
                val dateEnd = dateEnd.text.toString()
                val timeStart = timeStart.text.toString()
                val timeEnd = timeEnd.text.toString()
                val dStart : Date
                val dEnd : Date
                val tStart : Date
                val tEnd : Date

                var i = 0
                try{
                    dStart = dateFormat.parse(dateStart)
                    i++
                    dEnd = dateFormat.parse(dateEnd)
                    i++
                    tStart = timeFormat.parse(timeStart)
                    i++
                    tEnd = timeFormat.parse(timeEnd)
                }
                catch(e: Exception){
                    when(i){
                        0 -> Toast.makeText(requireContext(), "Invalid start date format",
                            Toast.LENGTH_SHORT).show()
                        1 -> Toast.makeText(requireContext(), "Invalid end date format",
                            Toast.LENGTH_SHORT).show()
                        2 -> Toast.makeText(requireContext(), "Invalid start time format",
                            Toast.LENGTH_SHORT).show()
                        3 -> Toast.makeText(requireContext(), "Invalid end time format",
                            Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(requireContext(), "Something's wrong",
                            Toast.LENGTH_SHORT).show()
                    }
                    return@setOnClickListener
                }

                if(dStart.after(dEnd)){
                    Toast.makeText(requireContext(), "Start date is after end date", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if(!dStart.before(dEnd) && tStart.after(tEnd)){
                    Toast.makeText(requireContext(), "Start time is after end time", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }


                if(title == "")
                    title = "New Event"


                GlobalScope.launch {

                    val db = DatabaseAndroid.getDatabase(requireContext())
                    val dao = db.eventDao()

                    dao.insert(Event(2, title, note, dateStart, dateEnd, timeStart, timeEnd))
                }


                parentFragmentManager.popBackStack()

            }
        }
    }
}