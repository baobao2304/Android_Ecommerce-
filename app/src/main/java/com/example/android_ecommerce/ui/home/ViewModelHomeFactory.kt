package com.example.android_ecommerce.ui.home

import androidx.lifecycle.ViewModelProvider
import com.example.android_ecommerce.respository.ProductRepository

class ViewModelHomeFactory(private val repository: ProductRepository):ViewModelProvider.NewInstanceFactory(){

}