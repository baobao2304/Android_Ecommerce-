package com.example.android_ecommerce.model
class Bill (idBill:String,userCus:String,allMoneyPr:Double,){
    var idBill: String? = null
    var allMoneyPr: Double? = null
    var userCus: String? = null
    init{
        this.idBill = idBill
        this.allMoneyPr = allMoneyPr
        this.userCus = userCus
    }
}