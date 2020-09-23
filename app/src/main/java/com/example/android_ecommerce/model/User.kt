package com.example.android_ecommerce.model

class User (
     idLog: String,
     id: String,
     name: String,
     pass: String,
     email: String,
     numberphone: String,
     address: String,
     imageUrl: String
){
    var idLog: String? = null
    var id: String? = null
    var name: String? = null
    var pass: String? = null
    var email: String? = null
    var numberphone: String? = null
    var address: String? = null
    var imageUrl: String? = null
    init{
        this.idLog = idLog
        this.id = id
        this.pass = pass
        this.email = email
        this.numberphone = numberphone
        this.address = address
        this.name = name
        this.imageUrl = imageUrl
    }
}