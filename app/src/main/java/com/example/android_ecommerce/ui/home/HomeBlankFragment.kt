package com.example.android_ecommerce.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_ecommerce.R
import com.example.android_ecommerce.SettingAll
import com.example.android_ecommerce.model.User
import com.example.android_ecommerce.ui.adapter.ProductAdapter
import com.example.android_ecommerce.ui.adapter.dataProduct
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlinx.android.synthetic.main.fragment_home_blank.view.*
import kotlinx.android.synthetic.main.fragment_login_register_blank.view.*


class HomeBlankFragment( ) : Fragment(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navDrawer: ImageView
    lateinit var txtLgAndLo: TextView
    private lateinit var adapter:ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_home_blank, container, false);
        view.navStore.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_homeBlankFragment_to_storeBlankFragment)}





//        val map = HashMap<String, String>()
//        var keyid = SettingAll.mData.child("Product").push().key
//        map.put("id", keyid.toString())
//        map.put("name", "Macbook Air 2017 MQD32 (13 inch) - Hàng Chính Hãng")
//        map.put("price", "18493000")
//        map.put("description", "Thiết kế tinh tế, sang trọng\n" +
//                "Macbook Air 2017 MQD32 có thiết kế tối giản nhưng không kém phần sang trọng. Toàn thân máy được gia công từ nhôm nguyên khối một cách tỉ mỉ và chính xác, tạo nên vẻ liền lạc và chắc chắn lại vừa thanh thoát, sang trọng. Nút Power khởi động máy được tích hợp luôn vào góc trên bên phải của bàn phím, vừa gọn vừa thẩm mỹ. Cả phần màn hình cũng được gia công kiểu Unibody hợp kim nhôm nguyên khối, các cạnh được bo tròn " +
//                "và dát mỏng tạo nên tổng thể máy một thiết kế tuyệt mỹ, tuyệt đẹp trong các dòng Ultrabook.")
//        map.put("imageUrl", "https://salt.tikicdn.com/cache/w390/ts/product/24/1b/e9/0771b005d8b7d4547b2a5fc0012d4721.jpg")
//        map.put("type", "Laptop")
//        map.put("supplier", "Apple")
//
//        SettingAll.mData.child("Product").push().setValue(map)

        HandleDataProduct()
        HandleRefer(view)
        RecyclerViewProduct(view)
        HandleDraw(view)
        NavigatePage(view)
        return view
    }
    fun HandleRefer(view: View){
        txtLgAndLo = view.findViewById<TextView>(R.id.txtLoginAndRegister)
    }
    fun HandleDraw(view: View){
        drawerLayout = view.findViewById(R.id.drawer_layout)
//        toolbar = view.findViewById(R.)
//        drawerLayout = view.findViewById()
        navDrawer = view.findViewById<ImageView>(R.id.navDrawer)

        navDrawer.setOnClickListener {
            openDrawer(drawerLayout)
        }

    }
    fun NavigatePage(view: View){
//        view.navStore.setOnClickListener {
//        Navigation.findNavController(view).navigate(R.id.action_homeBlankFragment_to_storeBlankFragment)}



        val sharedPreferences: SharedPreferences =
            view.getContext().getSharedPreferences("data_user", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "")
        val numberPhone = sharedPreferences.getString("numberPhone", "")
        val address = sharedPreferences.getString("address", "")
        val name = sharedPreferences.getString("name", "")
        val id = sharedPreferences.getString("id", "")
        val pass = sharedPreferences.getString("pass", "")
        val idLog = sharedPreferences.getString("idUser", "")

        SettingAll.userMain = User(idLog.toString(),id.toString(),name.toString(),pass.toString(),email.toString(),
            numberPhone.toString(),address.toString(),"")
        if(SettingAll.userMain == null || SettingAll.userMain.name == ""){
            txtLgAndLo.text = "Đăng nhập"
            view.txtUserName.text = ""
        }
        else{
            txtLgAndLo.text = "Đăng xuất"
            view.txtUserName.text = SettingAll.userMain.name
        }

        txtLgAndLo.setOnClickListener {
            if(txtLgAndLo.text.equals("Đăng nhập"))
                Navigation.findNavController(view).navigate(R.id.action_homeBlankFragment_to_loginRegisterBlankFragment)
            else{
                txtLgAndLo.text = "Đăng nhập"
                SettingAll.userMain = User("", "", "", "", "", "", "", "")
//                val sharedPreferences: SharedPreferences =
//                    view.getContext().getSharedPreferences("data_user", Context.MODE_PRIVATE)
                val edit = sharedPreferences.edit()
                edit.putString("id", "")
                edit.putString("pass", "")
                edit.putString("email", "")
                edit.putString("numberPhone", "")
                edit.putString("address", "")
                edit.putString("name", "")
                edit.putString("name", "")
                edit.putString("imageUrl", "")
                edit.commit()

            }
        }
        view.txtHistoryStore.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeBlankFragment_to_historyStoreBlankFragment)
        }
        view.txtNotification.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeBlankFragment_to_notificationBlankFragment)
        }
    }

    fun RecyclerViewProduct(view: View){
        adapter = ProductAdapter(view.context)
//        rcv:RecyclerView  = new RecyclerView()
        val rcv = view.findViewById<RecyclerView>(R.id.rcv_itemPr)

        rcv.layoutManager = LinearLayoutManager(view.context)
        rcv.adapter = adapter

//        val listProduct:MutableList<dataProduct> = mutableListOf<dataProduct>()
//        var img = "https://www.nhatbanaz.com/wp-content/uploads/nh%E1%BB%AFng-h%C3%ACnh-%E1%BA%A3nh-hoa-Anh-%C4%90%C3%A0o-2020-%C4%91%E1%BA%B9p-nh%E1%BA%A5t-3--620x413.jpg"
//        listProduct.add(
//            dataProduct("Sản Phẩm Cực kỳ tốt mô tả ", img, "SP 1", "500000", "1")
//        )
//        listProduct.add(
//            dataProduct("Sản Phẩm Cực kỳ tốt mô tả ", img, "SP 2", "500000", "1")
//        )
//        listProduct.add(
//            dataProduct("Sản Phẩm Cực kỳ tốt mô tả ", img, "SP 3", "500000", "1")
//        )
//        listProduct.add(
//            dataProduct("Sản Phẩm Cực kỳ tốt mô tả ", img, "SP 4", "500000", "1")
//        )
//        listProduct.add(
//            dataProduct("Sản Phẩm Cực kỳ tốt mô tả ", img, "SP 5", "500000", "1")
//        )
//        listProduct.add(
//            dataProduct("Sản Phẩm Cực kỳ tốt mô tả ", img, "SP 6", "500000", "1")
//        )
//        listProduct.add(
//            dataProduct("Sản Phẩm Cực kỳ tốt mô tả ", img, "SP 7", "500000", "1")
//        )
        adapter.setListData(SettingAll.listProduct)
        adapter.notifyDataSetChanged()
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
        when(item.itemId){
//            R.id,
        }
    }


    fun openDrawer(drawerLayout: DrawerLayout){
        drawerLayout.openDrawer(GravityCompat.START)
    }

    fun closeDrawer(drawerLayout: DrawerLayout){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun onPause() {
        super.onPause()
        closeDrawer(drawerLayout)
    }
//    data class dataProduct(var _description: String? = "",
//                           var _imageUrl: String? = "",
//                           var _nameProduct: String? = "",
//                           var _priceProduct: String? = "",
//                           var _idProduct: String? = "",
//                           var _typeProduct: String? = "",
//                           var _supplierProduct: String? = "")

    fun HandleDataProduct(){
        SettingAll.listProduct = SettingAll.listProductTemp

        if(SettingAll.listProduct.size.equals(0)){
            SettingAll.mData.child("Product").addChildEventListener(object: ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    var description = snapshot.child("description").getValue().toString()
                    var id = snapshot.child("id").getValue().toString()
                    var imageUrl = snapshot.child("imageUrl").getValue().toString()
                    var name = snapshot.child("name").getValue().toString()
                    var price = snapshot.child("price").getValue().toString()
                    var supplier = snapshot.child("supplier").getValue().toString()
                    var type = snapshot.child("type").getValue().toString()
                    SettingAll.listProduct.add(
                        dataProduct(
                            description.toString(),imageUrl.toString(),name.toString(),
                            price.toString(),id.toString(),type.toString(),supplier.toString()))
                    adapter.notifyDataSetChanged()
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onChildRemoved(snapshot: DataSnapshot) {

                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
//        LOAD MORE DATA

    }
}