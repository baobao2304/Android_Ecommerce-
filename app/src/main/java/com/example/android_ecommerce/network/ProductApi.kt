package com.example.android_ecommerce.network

import com.example.android_ecommerce.model.Product
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ProductApi {

    @GET("products")
    suspend fun getProducts() : Response<List<Product>>


    companion object{
        operator fun invoke() : ProductApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.simplifiedcoding.in/course-apis/recyclerview/")
                .build()
                .create(ProductApi::class.java)
        }
    }
}
