package co.lobolabs.eshopping.presentation.merchant.viewmodel

import co.lobolabs.eshopping.merchant.MerchantResponse

data class MerchantState(
    val isLoading: Boolean = false,
    val merchant: MerchantResponse? = null,
    val error: String? = null
)
