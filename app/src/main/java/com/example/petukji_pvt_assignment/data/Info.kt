package com.example.petukji_pvt_assignment.data

import com.google.firebase.database.Exclude

data class Info (
    @get:Exclude
    var id:String?=null,
    var fullname:String?=null,
    var phonenumber:String?=null,
    var address:String?=null,
    var city:String?=null
){
}