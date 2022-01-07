package com.example.petukji_pvt_assignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petukji_pvt_assignment.data.Info
import com.example.petukji_pvt_assignment.databinding.RecyclerViewContactBinding
class InfoAdapter: RecyclerView.Adapter<InfoAdapter.ViewHolder>() {
     var Info= mutableListOf<Info>()
    inner class ViewHolder(val binding : RecyclerViewContactBinding):RecyclerView.ViewHolder(binding.root)
    {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewContactBinding.inflate(LayoutInflater.from(parent.context),
            parent,false))
    }
//bind data to recyclerview
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textviewName.text=Info[position].fullname
        holder.binding.textviewPhone.text=Info[position].phonenumber
        holder.binding.textviewAddress.text=Info[position].address
        holder.binding.textviewCity.text=Info[position].city
    }

    override fun getItemCount(): Int {
      return Info.size
    }

    fun addinfo(info:Info)
    {
        if(!Info.contains(info))
        {
            Info.add(info)
        }else
        {
            val index=Info.indexOf(info)
             Info[index]=info
        }
        notifyDataSetChanged()
    }
}