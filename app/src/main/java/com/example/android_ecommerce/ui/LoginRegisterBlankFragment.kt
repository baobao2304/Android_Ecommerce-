package com.example.android_ecommerce.ui

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.example.android_ecommerce.R
import com.example.android_ecommerce.SettingAll
import com.example.android_ecommerce.model.User
import com.example.android_ecommerce.model.UserModel
import com.example.android_ecommerce.ui.adapter.LgLoPagerAdapter
import com.example.android_ecommerce.ui.adapter.dataProduct
import com.google.firebase.FirebaseError
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.annotations.Nullable
import kotlinx.android.synthetic.main.fragment_home_blank.view.*
import kotlinx.android.synthetic.main.fragment_login_register_blank.view.*


@Suppress("UNREACHABLE_CODE")
class LoginRegisterBlankFragment : Fragment() {
    var checkBtnLgLo = -1;
//    checkBtnLgLo = 0; == true
//    checkBtnLgLo = -1; == false
//    checkBtnLgLo = 1; == register
    var txtLogin: TextView? = null
    var txtRegister:TextView? = null
    val listUser:MutableList<User> = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_login_register_blank, container, false);
        txtLogin = view.findViewById<TextView>(R.id.txtLogin)
        txtRegister = view.findViewById<TextView>(R.id.txtRegister)
        // Inflate the layout for this fragment
        SettingAll.listUser = SettingAll.listUserTemp

        HandleDataUser(view)
        NavigateView(view)
        HandleViewPagerLogin(view)
        HandleLoginAndRegister(view)
        return view
    }
    fun NavigateView(view: View){
        view.btnBackHome.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginRegisterBlankFragment_to_homeBlankFragment)
        }
    }
    fun HandleLoginAndRegister(view: View){
        view.btnRegister.setOnClickListener {
            if(view.txtIdRegister.text.toString() == ("") || view.txtPassRegister.text.toString()==(""))
                checkBtnLgLo = -1
            else{
                var name:String = view.txtNameRegister.text.toString()
                var id:String = view.txtIdRegister.text.toString()
                var pass:String = view.txtPassRegister.text.toString()
                var numberphone:String = view.txtNumberPhone.text.toString()
                var email:String = view.txtEmail.text.toString()
                var address:String = view.txtAddressRegister.text.toString()
                val map = HashMap<String, String>()
                var keyid = SettingAll.mData.child("User").push().key

                var idUser:String = keyid.toString()

                map.put("idUser", idUser)
                map.put("name", view.txtNameRegister.text.toString())
                map.put("id", id)
                map.put("pass", pass)
                map.put("numberphone", numberphone)
                map.put("email", email)
                map.put("address", address)
                map.put("imageUrl", "")
                SettingAll.mData.child("User").push().setValue(map)

                val sharedPreferences = view.getContext().getSharedPreferences("data_user", Context.MODE_PRIVATE);
                val edit = sharedPreferences.edit()
                edit.putString("id", id)
                edit.putString("idUser", idUser)
                edit.putString("pass", pass)
                edit.putString("email", email)
                edit.putString("numberPhone", numberphone)
                edit.putString("address", address)
                edit.putString("name", name)
                edit.putString("imageUrl", "")
                edit.commit()


                SettingAll.userMain = User(idUser,id,name,pass,email,numberphone,address,"")

                listUser.add(SettingAll.userMain)

                checkBtnLgLo = 1
            }


            dialogDN(view)
        }
        view.btnLogin.setOnClickListener {


            for (item in SettingAll.listUser){
                if(view.txtIDLogin.text.toString()==(item.id) && view.txtPassLogin.text.toString()==(item.pass)){
                    SettingAll.userMain = item
                    checkBtnLgLo = 0
                    val sharedPreferences = view.getContext().getSharedPreferences("data_user", Context.MODE_PRIVATE);
                    val edit = sharedPreferences.edit()
                    edit.putString("id", item.id)
                    edit.putString("idUser", item.idLog)
                    edit.putString("pass", item.pass)
                    edit.putString("email", item.email)
                    edit.putString("numberPhone", item.numberphone)
                    edit.putString("address", item.address)
                    edit.putString("name", item.name)
                    edit.putString("imageUrl", item.imageUrl)
                    edit.commit()
                    break
                }
            }
            dialogDN(view)

        }
    }
    fun HandleViewPagerLogin(view: View){
        val adapter = LgLoPagerAdapter()
        val pager = view.findViewById(R.id.viewPager) as ViewPager

        pager.adapter = adapter

        pager.addOnPageChangeListener(object:ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position:Int) {
                if (pager.getCurrentItem() === 0)
                {
                    txtLogin?.setTextColor(Color.WHITE)
                    txtRegister?.setTextColor(getResources().getColor(R.color.colorWhiteFill))
                }
                else
                {
                    txtRegister?.setTextColor(Color.WHITE)
                    txtLogin?.setTextColor(getResources().getColor(R.color.colorWhiteFill))
                }
            }
        })
    }


    private fun dialogDN(view:View) {
        //Tạo đối tượng
        val b = AlertDialog.Builder(view.getContext())
        //Thiết lập tiêu đề
        b.setTitle("Thông Báo")
        if(checkBtnLgLo == 0){
            b.setMessage("Bạn Đã Đăng Nhập Thành Công")
            b.setPositiveButton("Yes", object: DialogInterface.OnClickListener {
                override fun onClick(dialog:DialogInterface, id:Int) {

                    Navigation.findNavController(view).navigate(R.id.action_loginRegisterBlankFragment_to_homeBlankFragment)

                }
            })
            val al = b.create()
            al.show()
        }
        else if(checkBtnLgLo == 1){
            b.setMessage("Bạn Đã Đăng Ký Thành Công")
            b.setPositiveButton("Yes", object: DialogInterface.OnClickListener {
                override fun onClick(dialog:DialogInterface, id:Int) {

                    Navigation.findNavController(view).navigate(R.id.action_loginRegisterBlankFragment_to_homeBlankFragment)

                }
            })
            val al = b.create()
            al.show()
        }

        else if(checkBtnLgLo == -1){
            b.setMessage("Bạn Đã Đăng Nhập Sai Mời Đăng Nhập Lại")
            b.setPositiveButton("Yes", object: DialogInterface.OnClickListener {
                override fun onClick(dialog:DialogInterface, id:Int) {
                }
            })
            val al = b.create()
            al.show()
        }


    }

    fun HandleDataUser(view: View){
        if(SettingAll.listUser.size.equals(0)){
            SettingAll.mData.child("User").addChildEventListener(object: ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    var idlog = snapshot.child("idUser").getValue().toString()
                    var id = snapshot.child("id").getValue().toString()
                    var name = snapshot.child("name").getValue().toString()
                    var pass = snapshot.child("pass").getValue().toString()
                    var email = snapshot.child("email").getValue().toString()
                    var np = snapshot.child("numberphone").getValue().toString()
                    var adres = snapshot.child("address").getValue().toString()
                    var img = snapshot.child("address").getValue().toString()
                    SettingAll.listUser.add(User(idlog.toString(),
                        id.toString(),name.toString(),pass.toString(),email.toString(),np.toString(),adres.toString(),img.toString()))

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