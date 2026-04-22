package co.lobolabs.eshopping.data.remote

import co.lobolabs.eshopping.di.ApiModule
import co.lobolabs.eshopping.merchant.MerchantResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class MerchantApi(
    private val client: HttpClient = ApiModule.client
) {

    suspend fun getMerchant(merchantId: Int): MerchantResponse {
        return client.get("http://127.0.0.1:8080/api/merchant/$merchantId").body()
    }
}
