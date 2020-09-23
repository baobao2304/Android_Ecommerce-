package com.example.android_ecommerce.model

import com.example.android_ecommerce.ui.adapter.dataProduct

class DetailBill (idBill:String,idDetailBill:String,dtPr:dataProduct,numberPr:Int){
    var idBill: String? = null
    var idDetailBill: String? = null
    var numberPr: Int? = 1
    var dtPr: dataProduct? = null

    init{
        this.idBill = idBill
        this.idDetailBill = idDetailBill
        this.numberPr = numberPr
        this.dtPr = dtPr
    }
}