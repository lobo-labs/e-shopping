package co.lobolabs.eshopping.features.merchant.data.repository

import co.lobolabs.eshopping.merchant.MerchantMock
import co.lobolabs.eshopping.features.merchant.domain.repository.MerchantRepository
import co.lobolabs.eshopping.merchant.MerchantResponse
import co.lobolabs.eshopping.schedule.MerchantSchedule

class MerchantRepositoryImpl(
    val database: MerchantMock = MerchantMock
) : MerchantRepository {
    override fun getMerchantById(id: Int): MerchantResponse? {
        return database.merchants.find { it.id == id }
    }

    override fun getMerchantSchedule(merchantId: Int): MerchantSchedule? {
        return database.schedules.find { it.merchantId == merchantId }
    }
}