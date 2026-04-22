package co.lobolabs.eshopping.presentation.merchant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.lobolabs.eshopping.data.MenuCategory
import co.lobolabs.eshopping.data.MenuItem
import co.lobolabs.eshopping.data.Merchant
import co.lobolabs.eshopping.merchant.MerchantMock
import co.lobolabs.eshopping.presentation.merchant.component.MerchantCategoryTabs
import co.lobolabs.eshopping.presentation.merchant.component.MerchantDeliveryOptions
import co.lobolabs.eshopping.presentation.merchant.component.MerchantFooter
import co.lobolabs.eshopping.presentation.merchant.component.MerchantHeader
import co.lobolabs.eshopping.presentation.merchant.component.MerchantInfoBottomSheet
import co.lobolabs.eshopping.presentation.merchant.component.MerchantInfoTabs
import co.lobolabs.eshopping.presentation.merchant.component.ProductDetailBottomSheet
import co.lobolabs.eshopping.presentation.merchant.component.ShippingMethodBottomSheet
import co.lobolabs.eshopping.presentation.merchant.viewmodel.MerchantEffect
import co.lobolabs.eshopping.presentation.merchant.viewmodel.MerchantIntent
import co.lobolabs.eshopping.presentation.merchant.viewmodel.MerchantState
import co.lobolabs.eshopping.presentation.merchant.viewmodel.MerchantViewModel
import co.lobolabs.eshopping.presentation.product.ProductItem
import co.lobolabs.eshopping.presentation.ui.EShoppingTheme
import co.lobolabs.eshopping.presentation.ui.extension.shimmerLoadingAnimation

@Composable
internal fun MerchantScreen(
    viewModel: MerchantViewModel = MerchantViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val effect = viewModel.effect.collectAsStateWithLifecycle(null).value

    LaunchedEffect(Unit) {
        viewModel.onIntent(MerchantIntent.GetMerchant(merchantId = 1))
    }

    LaunchedEffect(effect) {
        when (effect) {
            is MerchantEffect.OnError -> Unit
            else -> Unit
        }
    }

    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }


    val filteredItems by remember(searchQuery) {
        derivedStateOf {
            if (searchQuery.isBlank()) {
                MenuItem.items
            } else {
                MenuItem.items.filter {
                    it.title.contains(searchQuery, ignoreCase = true) ||
                            it.description.contains(searchQuery, ignoreCase = true)
                }
            }
        }
    }

    MerchantScreenContent(
        state = state,
        isSearching = isSearching,
        onIsSearchingChanged = { isSearching = it },
        searchQuery = searchQuery,
        onSearchQueryChange = { searchQuery = it },
        filteredItems = filteredItems
    )
}

@Composable
private fun MerchantScreenContent(
    state: MerchantState,
    isSearching: Boolean,
    onIsSearchingChanged: (Boolean) -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    filteredItems: List<MenuItem>
) {
    var selectedItem by remember { mutableStateOf<MenuItem?>(null) }

    var initialMerchantInfoTab by remember { mutableStateOf(MerchantInfoTabs.ABOUT) }
    var showMerchantInfo by remember { mutableStateOf(false) }
    var showShippingMethod by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MerchantHeader(
                isLoading = state.isLoading,
                merchant = state.merchant,
                isSearching = isSearching,
                onIsSearchingChange = onIsSearchingChanged,
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                onOpenMerchantInfo = {
                    initialMerchantInfoTab = it
                    showMerchantInfo = true
                }
            )
            MerchantDeliveryOptions(
                isLoading = state.isLoading,
                onOpenDeliveryInfo = {
                    showShippingMethod = true
                }
            )
            MerchantCategoryTabs(
                isLoading = state.isLoading,
                categories = MenuCategory.items
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 200.dp)
            ) {
                item {
                    if (state.isLoading) {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                                .width(100.dp)
                                .height(24.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .shimmerLoadingAnimation()
                        )
                    } else {
                        Text(
                            text = if (isSearching && searchQuery.isNotBlank()) "RESULTADOS" else "POKE",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }
                }
                if (state.isLoading) {
                    items(3) {
                        ProductItem(item = null, isLoading = true, onClick = {})
                    }
                } else {
                    items(filteredItems) { item ->
                        ProductItem(
                            item = item,
                            isLoading = false,
                            onClick = {
                                selectedItem = item
                            }
                        )
                    }
                }
                item {
                    MerchantFooter(merchant = Merchant.items.first())
                }
            }
        }

        if (showMerchantInfo) {
            MerchantInfoBottomSheet(
                merchant = Merchant.items.first(),
                initialTab = initialMerchantInfoTab,
                onDismissRequest = { showMerchantInfo = false }
            )
        }

        if (showShippingMethod) {
            ShippingMethodBottomSheet(
                onDismiss = {
                    showShippingMethod = false
                },
                onConfirm = {
                    showShippingMethod = false
                }
            )
        }

        selectedItem?.let { item ->
            ProductDetailBottomSheet(
                item = item,
                onDismissRequest = {
                    selectedItem = null
                },
                onAddToCart = { _, quantity, comment ->
                    println("Adicionado: ${item.title}, Qtd: $quantity, Obs: $comment")
                    selectedItem = null
                    // TODO adicionar ao carrinho
                }
            )
        }
    }
}


@Composable
@Preview
fun MerchantScreenLoadedPreview() {
    EShoppingTheme {
        MerchantScreenContent(
            state = MerchantState(
                isLoading = true
            ),
            isSearching = false,
            onIsSearchingChanged = {},
            searchQuery = "",
            onSearchQueryChange = {},
            filteredItems = MenuItem.items
        )
    }
}

@Composable
@Preview
fun MerchantScreenLoadingPreview() {
    EShoppingTheme {
        MerchantScreenContent(
            state = MerchantState(
                isLoading = false,
                merchant = MerchantMock.merchants.first()
            ),
            isSearching = false,
            onIsSearchingChanged = {},
            searchQuery = "",
            onSearchQueryChange = {},
            filteredItems = MenuItem.items
        )
    }
}
