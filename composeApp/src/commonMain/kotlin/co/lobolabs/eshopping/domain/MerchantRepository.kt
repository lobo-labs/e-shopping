package co.lobolabs.eshopping.domain

import co.lobolabs.eshopping.merchant.MerchantResponse

internal interface MerchantRepository {
    suspend fun getMerchant(merchantId: Int): MerchantResponse?
}