package com.example.petukji_pvt_assignment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.petukji_pvt_assignment.R
import com.example.petukji_pvt_assignment.databinding.FragmentInfoBinding


class InfoFragment : Fragment() {
    private var _binding:FragmentInfoBinding?=null
    private val binding get()=_binding!!
    private val adapter= InfoAdapter()

     private lateinit var viewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding= FragmentInfoBinding.inflate(inflater,container,false)
        viewModel= ViewModelProviders.of(this).get(ContactViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    binding.recyclerViewContacts.adapter= adapter
        binding.addButton.setOnClickListener{
            AddInfoDialogFragment().show(childFragmentManager," ")
        }
        viewModel.info.observe(viewLifecycleOwner, Observer {
            adapter.addinfo(it)
        })

        viewModel.getRealtimeUpdate()

        val itemTouchHelper=ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewContacts)
    }
    private var simpleCallback=object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT))
    {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
           var position=viewHolder.adapterPosition
            var currentinfo=adapter.Info[position]
            when(direction)
            {
                 ItemTouchHelper.RIGHT->{
                     UpdateInfoDialogFragment(currentinfo).show(childFragmentManager,"")
                 }
            }
            binding.recyclerViewContacts.adapter?.notifyDataSetChanged()
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}