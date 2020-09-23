package com.example.android_ecommerce

import android.widget.TextView
import com.example.android_ecommerce.model.DetailBill
import com.example.android_ecommerce.model.User
import com.example.android_ecommerce.ui.adapter.dataProduct
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

internal object SettingAll {
    var userMain:User = User("","", "", "", "", "", "", "")
    var itemProduct:dataProduct = dataProduct("", "", "", "", "")
    var listBill = mutableListOf<DetailBill>()
    var listProduct:MutableList<dataProduct> = mutableListOf<dataProduct>()
    val listProductTemp:MutableList<dataProduct> = mutableListOf<dataProduct>()
    var listUser = mutableListOf<User>()
    var listUserTemp = mutableListOf<User>()
    lateinit var txtMoney: TextView
    var mData: DatabaseReference = Firebase.database.reference
}