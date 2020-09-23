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
import com.example.android_ecommerce.model.Product
import com.facebook.drawee.gestures.GestureDetector
import kotlinx.android.synthetic.main.fragment_home_blank.view.*
import kotlinx.android.synthetic.main.item_recyclerview_product.view.*

class ProductAdapter(private val context: Context):RecyclerView.Adapter<ProductAdapter.ProductsViewHolder>() {
//    lateinit var _context:Context
    private var listPR = mutableListOf<dataProduct>()

    fun setListData(data:MutableList<dataProduct>){
        listPR = data
    }
    override fun getItemCount(): Int {
         if (listPR.size > 0)
             return listPR.size
         else
             return 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ProductsViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_product,parent,false)
//        var prvhd:ProductsViewHolder =  ProductsViewHolder(view)
        return ProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val itemPr:dataProduct = listPR[position]
        holder.bindView(itemPr)
    }

    inner class ProductsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {


        fun bindView(itemPr:dataProduct){
            Glide.with(context).load(itemPr._imageUrl).into(itemView.imageMainPR)
            itemView.txtNamePr.text = itemPr._nameProduct
            itemView.txtPricePR.text = itemPr._priceProduct
            itemView.setOnClickListener {
                SettingAll.itemProduct = itemPr
                Navigation.findNavController(itemView).navigate(R.id.action_homeBlankFragment_to_detailPrBlankFragment)
            }
        }


    }
}