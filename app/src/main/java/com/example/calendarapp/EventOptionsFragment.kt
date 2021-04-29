package com.example.calendarapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EventOptionsFragment(val fm : FragmentManager, val toDelete: Event): Fragment(R.layout.fragment_event_options){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setCancelable(true)
        builder.setTitle("Delete this event?")
        builder.setMessage("Once deleted, it cannot be recovered")
        builder.setPositiveButton("Delete"
        ) { _, _ ->
            GlobalScope.launch{
                val db = DatabaseAndroid.getDatabase(requireContext())
                val dao = db.eventDao()

                dao.delete(toDelete)
                fm.commit { replace<EventsListFragment>(R.id.fragment_container_view) }

            }
          }
        builder.setNegativeButton("Cancel"
        ) { _, _ -> }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}