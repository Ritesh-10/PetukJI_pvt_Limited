package com.example.petukji_pvt_assignment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.petukji_pvt_assignment.R
import com.example.petukji_pvt_assignment.data.Info
import com.example.petukji_pvt_assignment.databinding.FragmentAddInfoDialogBinding


@Suppress("DEPRECATION")
class AddInfoDialogFragment : DialogFragment() {

        private var _binding:FragmentAddInfoDialogBinding?=null
        private val binding get() = _binding!!


       private lateinit var viewModel: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentAddInfoDialogBinding.inflate(inflater,container,false)
        viewModel= ViewModelProviders.of(this).get(ContactViewModel::class.java)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

      viewModel.result.observe(viewLifecycleOwner, Observer {
          val message=if(it==null)
          {
              getString(R.string.added_information)
          }else{
              getString(R.string.error,it.message)
          }
          Toast.makeText(requireContext(),message , Toast.LENGTH_SHORT).show()
          dismiss()
      })
        binding.buttonAdd.setOnClickListener{
            val fullName=binding.editTextFullName.text.toString().trim()
            val phonenumber=binding.editTextNumber.text.toString().trim()
            val address=binding.editTextAddress.text.toString().trim()
            val city=binding.editTextCity.text.toString().trim()

            if(fullName.isEmpty()){
                binding.editTextFullName.error="This field is required"
                return@setOnClickListener
            }
            if(phonenumber.isEmpty()){
                binding.editTextNumber.error="This field is required"
                return@setOnClickListener
            }
            if(address.isEmpty()){
                binding.editTextAddress.error="This field is required"
                return@setOnClickListener
            }
            if(city.isEmpty()){
                binding.editTextCity.error="This field is required"
                return@setOnClickListener
            }
            val info=Info()
            info.fullname=fullName
            info.phonenumber=phonenumber
            info.address=address
            info.city=city

            viewModel.addInformation(info)
        }
    }

}