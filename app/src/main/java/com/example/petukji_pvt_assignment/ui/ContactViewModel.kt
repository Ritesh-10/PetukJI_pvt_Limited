package com.example.petukji_pvt_assignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petukji_pvt_assignment.data.Info
import com.example.petukji_pvt_assignment.data.NODE_INFORMATION
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception

class ContactViewModel:ViewModel() {
    private val dbcontacts=FirebaseDatabase.getInstance().getReference(NODE_INFORMATION)
    //checking if data is successfully saved or not
    private val _result=MutableLiveData<Exception?>()
    val result:LiveData<Exception?>get()=_result

//for displaying from firebase to phone
    private val _contact=MutableLiveData<Info>()
    val info:LiveData<Info>get()=_contact
    fun addInformation(info:Info)
    {
        info.id=dbcontacts.push().key

        dbcontacts.child(info.id!!).setValue(info).addOnCompleteListener{
            if(it.isSuccessful)
            {
                _result.value=null
            }else{
                _result.value=it.exception
            }
        }
    }

    private val childEventListener=object : ChildEventListener {
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val contact=snapshot.getValue(Info::class.java)
            contact?.id=snapshot.key
            _contact.value=contact!!
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            val contact=snapshot.getValue(Info::class.java)
            contact?.id=snapshot.key
            _contact.value=contact!!
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            TODO("Not yet implemented")
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    }
    //adding,reading,displaying data
    fun getRealtimeUpdate()
    {
        dbcontacts.addChildEventListener(childEventListener)
    }

    fun updateInfo(info: Info )
    {
        dbcontacts.child(info.id!!).setValue(info)
            .addOnCompleteListener{
                if(it.isSuccessful)
                {
                    _result.value=null
                }else{
                    _result.value=it.exception
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        dbcontacts.removeEventListener(childEventListener)
    }
}