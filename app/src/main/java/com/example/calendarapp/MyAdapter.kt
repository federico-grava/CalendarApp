package com.example.calendarapp

import android.annotation.SuppressLint //remove this later
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.databinding.RecyclerElementBinding
import java.text.SimpleDateFormat

class MyAdapter(var mData : MutableList<Event>, var fm : FragmentManager) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    /*init {
        if(mData.isEmpty()){
            mData.add(0, Event(0,"empty", "empty", "0", "0", "0", "0"))
        }
    }*/

    class MyViewHolder(val binding : RecyclerElementBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerElementBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    //@SuppressLint("SetTextI18n") //remove this later
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(holder.binding) {

            recTitle.text = mData[position].title
            recDate.text = mData[position].startDate
            recTime.text = mData[position].startTime
            recNote.text = (mData[position].note?: "").take(80)

            root.setOnClickListener {
                val event = EventDetailsFragment(mData[position].title,
                                "Start: " + mData[position].startDate + " " + mData[position].startTime,
                                "End: " + mData[position].endDate + " " + mData[position].endTime,
                                        mData[position].note)

                fm.commit {
                    setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out)
                    replace(R.id.fragment_container_view, event)
                    setReorderingAllowed(true)
                    addToBackStack("EventsList")
                }
            }

            root.setOnLongClickListener {
                fm.commit {
                    setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out)
                    replace(R.id.fragment_container_view_2, EventOptionsFragment(fm, mData[position]))
                    setReorderingAllowed(true)
                    addToBackStack("EventsList")
                }
                true
            }
        }

    }

    override fun getItemCount(): Int = mData.size
}