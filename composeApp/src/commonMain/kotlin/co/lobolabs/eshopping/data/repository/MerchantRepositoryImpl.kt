package co.lobolabs.eshopping.data.repository

import co.lobolabs.eshopping.data.remote.MerchantApi
import co.lobolabs.eshopping.domain.MerchantRepository
import co.lobolabs.eshopping.merchant.MerchantResponse

internal class MerchantRepositoryImpl(
    private val api: MerchantApi = MerchantApi(),
) : MerchantRepository {
    override suspend fun getMerchant(merchantId: Int): MerchantResponse? {
        return try {
            api.getMerchant(merchantId)
        } catch (e: Exception) {
            println("Error fetching merchant: ${e.message}")
            e.printStackTrace()
            null
        }
    }
}