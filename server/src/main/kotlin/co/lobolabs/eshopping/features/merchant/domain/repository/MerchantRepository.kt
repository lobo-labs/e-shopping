package co.lobolabs.eshopping.features.merchant.domain.repository

import co.lobolabs.eshopping.merchant.MerchantResponse
import co.lobolabs.eshopping.schedule.MerchantSchedule

interface MerchantRepository {
    fun getMerchantById(id: Int): MerchantResponse?
    fun getMerchantSchedule(merchantId: Int): MerchantSchedule?
}