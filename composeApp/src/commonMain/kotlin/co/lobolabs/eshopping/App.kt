package co.lobolabs.eshopping

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import co.lobolabs.eshopping.presentation.merchant.MerchantScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        MerchantScreen()
    }
}