package com.example.android_ecommerce.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.android_ecommerce.R
import com.example.android_ecommerce.SettingAll
import com.example.android_ecommerce.model.Bill
import com.example.android_ecommerce.model.DetailBill
import com.example.android_ecommerce.ui.adapter.dataProduct
import kotlinx.android.synthetic.main.fragment_detail_pr_blank.view.*
import kotlinx.android.synthetic.main.fragment_login_register_blank.view.*


class DetailPrBlankFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_detail_pr_blank, container, false)
        HandleData(view)
        HandleNavigate(view)
        return view
    }

    fun HandleData(view: View){
        Glide.with(view).load(SettingAll.itemProduct._imageUrl).into(view.imgPr)
        view.txtNamePrDt.text = SettingAll.itemProduct._nameProduct
        view.txtPricePrDt.text = "Price: "+SettingAll.itemProduct._priceProduct + " VND "
        view.txtDecription.text = "Decriptions : "+SettingAll.itemProduct._description
        if(SettingAll.userMain.id == "")
            view.txtDelivery.text = ""
        else
            view.txtDelivery.text = SettingAll.userMain.address
        view.btnAddStore.setOnClickListener {
//            SettingAll.listBill.add()
        }
    }
    fun HandleNavigate(view: View){
        view.imgBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_detailPrBlankFragment_to_homeBlankFragment)
        }
        view.btnAddStore.setOnClickListener {
            SettingAll.listBill.add(DetailBill("","",SettingAll.itemProduct,1))
            Navigation.findNavController(view).navigate(R.id.action_detailPrBlankFragment_to_storeBlankFragment)
        }
    }


}