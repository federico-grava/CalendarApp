package com.example.calendarapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.calendarapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val eventsListFrag = EventsListFragment()

        val fragmentManager = supportFragmentManager
        fragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, eventsListFrag)
        }
    }
}