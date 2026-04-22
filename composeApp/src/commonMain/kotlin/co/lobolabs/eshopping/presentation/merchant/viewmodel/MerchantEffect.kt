package co.lobolabs.eshopping.presentation.merchant.viewmodel

sealed class MerchantEffect {
    data class OnError(val error: String) : MerchantEffect()
}