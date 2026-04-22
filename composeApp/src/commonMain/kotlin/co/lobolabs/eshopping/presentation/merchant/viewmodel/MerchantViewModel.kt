package co.lobolabs.eshopping.presentation.merchant.viewmodel

import androidx.lifecycle.ViewModel
import co.lobolabs.eshopping.data.repository.MerchantRepositoryImpl
import co.lobolabs.eshopping.domain.MerchantRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

internal class MerchantViewModel(
    private val repository: MerchantRepository = MerchantRepositoryImpl()
) : ViewModel() {
    private val _state = MutableStateFlow(MerchantState())
    val state = _state.asStateFlow()

    private val _effect = Channel<MerchantEffect>()
    val effect = _effect.receiveAsFlow()

    fun onIntent(intent: MerchantIntent) {
        when (intent) {
            is MerchantIntent.GetMerchant -> {
                runCatching {
                    _state.update { it.copy(isLoading = true) }
                    val merchant = repository.getMerchant(intent.merchantId)
                    if (merchant == null) {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                merchant = null,
                                error = "merchant not found"
                            )
                        }
                        return
                    }
                    _state.update { it.copy(isLoading = false, merchant = merchant, error = null) }
                }.onFailure {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            merchant = null,
                            error = "get merchant fail"
                        )
                    }
                }
            }
        }
    }
}