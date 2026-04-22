package co.lobolabs.eshopping.data.repository

import co.lobolabs.eshopping.data.remote.MerchantApi
import co.lobolabs.eshopping.domain.MerchantRepository
import co.lobolabs.eshopping.merchant.MerchantResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class MerchantRepositoryImpl(
    private val api: MerchantApi = MerchantApi(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : MerchantRepository {
    override suspend fun getMerchant(merchantId: Int): MerchantResponse? = withContext(dispatcher) {
        return@withContext try {
            api.getMerchant(merchantId)
        } catch (_: Exception) {
            null
        }
    }
}