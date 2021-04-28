package com.example.calendarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendarapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var dateFormat = SimpleDateFormat("dd-MM-yyyy")
    private var timeFormat = SimpleDateFormat("hh:mm")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //val bundle = Bundle()
        val eventsListFrag = EventsListFragment()
        //fragobj.arguments = bundle

        val fragmentManager = supportFragmentManager
        //val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_events_list,eventsListFrag)
        }



        //val arr = createEventsList()

        with(binding) {
           //testo.text="hey"
          /* myRecyclerView.adapter = MyAdapter(arr)
           myRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)

            buttset.setOnClickListener {

                val s = testo.text.toString()
                try{val useless = dateFormat.parse(s)}
                catch(e: ParseException){
                    Toast.makeText(this@MainActivity, "Invalid date",
                            Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                GlobalScope.launch {

                    val db = DatabaseAndroid.getDatabase(this@MainActivity)
                    val dao = db.eventDao()

                    dao.insert(Event(2,"pulsanteClick", "forse", s, "12-12-2021", "13:56", "14:30"))
                }


            }

            buttget.setOnClickListener {

            } */

           /*buttget.setOnClickListener {
               var t : String
               GlobalScope.launch {
                   val db = DatabaseAndroid.getDatabase(this@MainActivity)
                   val dao = db.userDao()

                   val user = dao.getAll()[0]
                   t = (user.firstName + " " + user.lastName + " " + user.uid)
               }
               //testo.setText(t)

           }

           buttset.setOnClickListener {

               val s = testo.text.toString()
               GlobalScope.launch {

                   val db = DatabaseAndroid.getDatabase(this@MainActivity)
                   val dao = db.userDao()

                   dao.insert(User(3, s, "hey"))
               }


           }*/
        }
    }

/*    private fun createEventsList() : ArrayList<Event>{


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
    }*/
}