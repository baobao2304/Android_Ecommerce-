package com.example.android_ecommerce.respository

import com.example.android_ecommerce.network.ProductApi

class ProductRepository(private val api: ProductApi) {

    class ProductsRepository(
        private val api: ProductApi
    ) : SafeApiRequest() {

        suspend fun getProducts() = apiRequest { api.getProducts() }

    }
}