package co.lobolabs.eshopping.schedule

import kotlinx.serialization.Serializable

@Serializable
data class MerchantSchedule(
    val merchantId: Int,
    val schedule: List<MerchantScheduleTimes>
)

@Serializable
data class MerchantScheduleTimes(
    val dayOfWeek: String,
    val openTime: String,
    val closeTime: String
)
