package com.example.android_ecommerce.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserModel(
    var idLog: String? = "",
    var id: String? = "",
    var name: String? = "",
    var pass: String? = "",
    var email: String? = "",
    var numberphone: String? = "",
    var address: String? = "",
    var imageUrl: String? = ""
){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "idUser" to idLog,
            "name" to name,
            "pass" to pass,
            "email" to email,
            "numberphone" to numberphone,
            "address" to address,
            "imageUrl" to imageUrl
        )
    }
}