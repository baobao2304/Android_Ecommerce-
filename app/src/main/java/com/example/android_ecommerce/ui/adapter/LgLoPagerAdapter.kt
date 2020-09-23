package com.example.android_ecommerce.ui.adapter

import android.view.View
import androidx.viewpager.widget.PagerAdapter
import com.example.android_ecommerce.R

internal class LgLoPagerAdapter: PagerAdapter() {


    override fun getCount(): Int {
        return 2 as Int
    }

    override fun instantiateItem(collection: View, position:Int):Any {
        var resId = 0
        when (position) {
            0 -> resId = R.id.viewpager1
            1 -> resId = R.id.viewpager2
        }
        return collection.findViewById(resId)
    }
    override fun isViewFromObject(arg0:View, arg1:Any):Boolean {
        return arg0 === (arg1 as View)
    }
}
