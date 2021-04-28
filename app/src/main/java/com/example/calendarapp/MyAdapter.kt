package com.example.calendarapp

import android.annotation.SuppressLint //remove this later
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapp.databinding.RecyclerElementBinding
import java.text.SimpleDateFormat

class MyAdapter(private var mData : List<Event>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val binding : RecyclerElementBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RecyclerElementBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n") //remove this later
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //var dateFormat = SimpleDateFormat("dd.mm.yyyy")

        with(holder.binding) {

            titolo.text = "titolo: ${mData[position].title}"
            data.text = "data: ${mData[position].startDate}"
            anteprima.text = "anteprima: ${mData[position].note}"


        }

    }

    override fun getItemCount(): Int = mData.size
}