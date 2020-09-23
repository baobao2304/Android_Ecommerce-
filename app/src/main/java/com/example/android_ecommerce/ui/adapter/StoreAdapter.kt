package com.example.android_ecommerce.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_ecommerce.R
import com.example.android_ecommerce.SettingAll
import com.example.android_ecommerce.model.DetailBill
import com.example.android_ecommerce.model.Product
import com.example.android_ecommerce.ui.StoreBlankFragment
import com.facebook.drawee.gestures.GestureDetector
import kotlinx.android.synthetic.main.fragment_home_blank.view.*
import kotlinx.android.synthetic.main.item_rcv_productstore.view.*
import kotlinx.android.synthetic.main.item_recyclerview_product.view.*
import kotlinx.android.synthetic.main.item_recyclerview_productstore.view.*
import kotlinx.android.synthetic.main.item_recyclerview_productstore.view.btnAddSt
import kotlinx.android.synthetic.main.item_recyclerview_productstore.view.btnRemoveSt
import kotlinx.android.synthetic.main.item_recyclerview_productstore.view.imgPrSt
import kotlinx.android.synthetic.main.item_recyclerview_productstore.view.txtNamePrSt
import kotlinx.android.synthetic.main.item_recyclerview_productstore.view.txtPricePrSt

class StoreAdapter(private val context: Context):RecyclerView.Adapter<StoreAdapter.StoresViewHolder>() {
//    lateinit var _context:Context
    private var listBill = mutableListOf<DetailBill>()
    private var moneyPr:Double = 0.0
    fun setListData(data:MutableList<DetailBill>){
        listBill = data
    }
    override fun getItemCount(): Int {
         if (listBill.size > 0)
             return listBill.size
         else
             return 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):StoresViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.item_rcv_productstore,parent,false)
//        var prvhd:ProductsViewHolder =  ProductsViewHolder(view)
        return StoresViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoresViewHolder, position: Int) {
        val itemBill:DetailBill = listBill[position]
        holder.bindView(itemBill,position)
    }

    inner class StoresViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {


        fun bindView(itemBill:DetailBill,i:Int){
            var price1 = itemBill.dtPr?._priceProduct?.toDouble()
            var priceAllMn = SettingAll.txtMoney.text.toString().toDouble()
            if (price1 != null) {
                priceAllMn += price1
            }
            SettingAll.txtMoney.text = priceAllMn.toString()

            Glide.with(context).load(itemBill.dtPr?._imageUrl).into(itemView.imgPrSt)
            itemView.txtNamePrSt.text = itemBill.dtPr?._nameProduct
            itemView.txtPricePrSt.text = itemBill.dtPr?._priceProduct
            itemView.txtNumberSt.text = "1"
            itemView.btnAddSt.setOnClickListener {
                var num = (itemView.txtNumberSt.text).trim().toString().toInt()
                num++
                var price =  itemView.txtPricePrSt.text.trim().toString().toDouble()
                var pricepr = itemBill.dtPr?._priceProduct.toString().toDouble()
                priceAllMn += (pricepr)
                itemView.txtPricePrSt.text = (price + pricepr).toString()
                itemView.txtNumberSt.text = num.toString()
                SettingAll.txtMoney.text = priceAllMn.toString()
            }
            itemView.btnRemoveSt.setOnClickListener {
                var num = (itemView.txtNumberSt.text).trim().toString().toInt()
                if(num >0){
                    num--
                    var price =  itemView.txtPricePrSt.text.trim().toString().toDouble()
                    var pricepr = itemBill.dtPr?._priceProduct.toString().toDouble()
                    priceAllMn -= (pricepr)
                    itemView.txtPricePrSt.text = (price - pricepr).toString()
                    itemView.txtNumberSt.text = num.toString()
                    SettingAll.txtMoney.text = priceAllMn.toString()
                }
            }
            listBill[i].numberPr = itemView.txtNumberSt.text.toString().toInt()
        }


    }
}