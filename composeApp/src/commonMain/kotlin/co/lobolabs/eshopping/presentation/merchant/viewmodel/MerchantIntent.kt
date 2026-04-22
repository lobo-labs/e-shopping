package co.lobolabs.eshopping.presentation.merchant.viewmodel

sealed class MerchantIntent {
    data class GetMerchant(val merchantId: Int) : MerchantIntent()
}