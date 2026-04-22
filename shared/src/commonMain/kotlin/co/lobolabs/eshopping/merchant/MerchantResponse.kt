package co.lobolabs.eshopping.merchant

import co.lobolabs.eshopping.schedule.MerchantScheduleTimes
import kotlinx.serialization.Serializable

@Serializable
data class MerchantResponse(
    val id: Int,
    val username: String,
    val name: String,
    val email: String,
    val phone: String,
    val categories: List<String>,
    val address: String,
    val schedule: List<MerchantScheduleTimes>,
    val scheduleStatus: String,
    val isOpen: Boolean
)
