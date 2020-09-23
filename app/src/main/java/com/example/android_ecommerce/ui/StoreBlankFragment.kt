package com.example.android_ecommerce.ui

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_ecommerce.R
import com.example.android_ecommerce.SettingAll
import com.example.android_ecommerce.ui.adapter.ProductAdapter
import com.example.android_ecommerce.ui.adapter.StoreAdapter
import kotlinx.android.synthetic.main.fragment_store_blank.view.*

class StoreBlankFragment : Fragment() {
    private lateinit var adapter:StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_store_blank, container, false)
        HandleNavigate(view)
        HandleRecycleView(view)
        return view
    }

    fun HandleNavigate(view: View){
        view.btnBuyPrDt.setOnClickListener {
            dialogDN(view)
        }
        view.imgBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_storeBlankFragment_to_homeBlankFragment)
        }
    }
    fun HandleRecycleView(view: View){
        adapter = StoreAdapter(view.context)
//        rcv:RecyclerView  = new RecyclerView()
        val rcv = view.findViewById<RecyclerView>(R.id.rcv_itemPrStore)
        SettingAll.txtMoney = view.findViewById<TextView>(R.id.txtAllMoneySt)
        SettingAll.txtMoney.text = "0"
        rcv.layoutManager = LinearLayoutManager(view.context)
        rcv.adapter = adapter
        adapter.setListData(SettingAll.listBill)
        adapter.notifyDataSetChanged()
    }
    private fun dialogDN(view:View) {
        //Tạo đối tượng
        val b = AlertDialog.Builder(view.getContext())
        //Thiết lập tiêu đề
        b.setTitle("Thông Báo")
        b.setMessage("Bạn Đã Đặt Hàng Thành Công")
        b.setPositiveButton("Yes", object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, id:Int) {
                val mBuilder = NotificationCompat.Builder(view.context, "chandle1")
                    .setSmallIcon(R.drawable.ic_baseline_message_24)
                    .setContentTitle("Bạn Đã Đặt Hàng Thành Công")
                    .setContentText("Xin chờ xác nhận hóa đơn")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                val notificationManager = NotificationManagerCompat.from(view.context)
                notificationManager.notify(Integer.parseInt("1"), mBuilder.build())

                val mapBill = HashMap<String, String>()
                var keyidBill = SettingAll.mData.child("Bill").push().key
                mapBill.put("id", keyidBill.toString())
                mapBill.put("idUser", SettingAll.userMain.idLog.toString())
                mapBill.put("AllMoney",SettingAll.txtMoney.text.toString())
                SettingAll.mData.child("Bill").push().setValue(mapBill)

                for(item in SettingAll.listBill){
                    val mapDetailBill = HashMap<String, String>()
                    var keyidDetailBill = SettingAll.mData.child("DetailBill").push().key
                    mapDetailBill.put("id", keyidDetailBill.toString())
                    mapDetailBill.put("idBill", keyidBill.toString())
                    mapDetailBill.put("idProduct", item.dtPr?._idProduct.toString())
                    mapDetailBill.put("numProduct", item.numberPr.toString())
                    SettingAll.mData.child("DetailBill").push().setValue(mapDetailBill)
                }


                Navigation.findNavController(view).navigate(R.id.action_storeBlankFragment_to_homeBlankFragment)

            }
        })
        val al = b.create()
        al.show()

    }
}