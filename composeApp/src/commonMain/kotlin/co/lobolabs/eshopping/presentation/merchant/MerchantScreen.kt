package co.lobolabs.eshopping.presentation.merchant

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.lobolabs.eshopping.data.MenuCategory
import co.lobolabs.eshopping.data.MenuItem
import co.lobolabs.eshopping.data.Merchant
import co.lobolabs.eshopping.presentation.merchant.component.MerchantCategoryTabs
import co.lobolabs.eshopping.presentation.merchant.component.MerchantHeader
import co.lobolabs.eshopping.presentation.merchant.component.MerchantInfoBottomSheet
import co.lobolabs.eshopping.presentation.merchant.component.MerchantInfoTabs
import co.lobolabs.eshopping.presentation.merchant.component.ShippingMethodBottomSheet
import co.lobolabs.eshopping.presentation.ui.EShoppingTheme
import eshopping.composeapp.generated.resources.Res
import eshopping.composeapp.generated.resources.ic_delivery
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

fun Modifier.shimmerLoadingAnimation(): Modifier = composed {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition()
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    background(brush)
}


@Composable
fun MerchantScreen(isLoadingInitial: Boolean = true) {
    var isLoading by remember { mutableStateOf(isLoadingInitial) }
    var showMerchantInfo by remember { mutableStateOf(false) }
    var showShippingMethod by remember { mutableStateOf(false) }
    var initialInfoTab by remember { mutableStateOf(MerchantInfoTabs.ABOUT) }

    if (isLoadingInitial) {
        LaunchedEffect(Unit) {
            delay(3000) // Simula carregamento de 3 segundos
            isLoading = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MerchantHeader(
                isLoading = isLoading,
                merchant = Merchant.items.first(),
                onOpenSchedule = {
                    initialInfoTab = MerchantInfoTabs.SCHEDULE
                    showMerchantInfo = true
                }
            )
            DeliveryOptions(
                isLoading = isLoading,
                onOpenDeliveryInfo = {
                    showShippingMethod = true
                }
            )
            MerchantCategoryTabs(
                isLoading = isLoading,
                categories = MenuCategory.items
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 200.dp)
            ) {
                item {
                    if (isLoading) {
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
                            text = "POKE",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }
                }
                if (isLoading) {
                    items(3) {
                        ProductItem(item = null, isLoading = true)
                    }
                } else {
                    items(MenuItem.items) { item ->
                        ProductItem(item = item, isLoading = false)
                    }
                }
            }
        }

        if (showMerchantInfo) {
            MerchantInfoBottomSheet(
                merchant = Merchant.items.first(),
                initialTab = initialInfoTab,
                onDismissRequest = { showMerchantInfo = false }
            )
        }

        if (showShippingMethod) {
            ShippingMethodBottomSheet(
                onDismiss = { showShippingMethod = false },
                onConfirm = {
                    showShippingMethod = false
                }
            )
        }
    }
}

@Composable
fun DeliveryOptions(
    isLoading: Boolean,
    onOpenDeliveryInfo: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (isLoading) {
            Card(
                modifier = Modifier.weight(1f).height(56.dp).shimmerLoadingAnimation(),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                border = BorderStroke(1.dp, Color(0xFFDEE2E6)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize())
            }
            Card(
                modifier = Modifier.weight(1f).height(56.dp).shimmerLoadingAnimation(),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                border = BorderStroke(1.dp, Color(0xFFDEE2E6)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize())
            }
        } else {
            Card(
                modifier = Modifier.weight(1f).height(56.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFDEE2E6)),
                shape = RoundedCornerShape(8.dp),
                onClick = onOpenDeliveryInfo
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_delivery),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Entrega", fontSize = 14.sp, modifier = Modifier.weight(1f))
                    Icon(
                        Icons.Default.KeyboardArrowDown,
                        null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.Gray
                    )
                }
            }
            Card(
                modifier = Modifier.weight(1f).height(56.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color(0xFFDEE2E6)),
                shape = RoundedCornerShape(8.dp),
                onClick = onOpenDeliveryInfo
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Hoje", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("30 - 40 min", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}


@Composable
fun ProductItem(item: MenuItem?, isLoading: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(0.5.dp, Color(0xFFEEEEEE))
    ) {
        Row(modifier = Modifier.height(140.dp)) {
            if (isLoading || item == null) {
                Box(
                    modifier = Modifier
                        .width(140.dp)
                        .fillMaxHeight()
                        .shimmerLoadingAnimation()
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(16.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .shimmerLoadingAnimation()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(12.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .shimmerLoadingAnimation()
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(12.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .shimmerLoadingAnimation()
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(60.dp)
                            .height(14.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .shimmerLoadingAnimation()
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .width(140.dp)
                        .fillMaxHeight()
                        .background(Color(0xFFF0F0F0))
                ) {
                    // Aqui entraria a imagem real
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFE0E0E0))
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = item.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            color = Color(0xFF212529)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = item.description,
                            fontSize = 13.sp,
                            color = Color.Gray,
                            maxLines = 3,
                            lineHeight = 18.sp
                        )
                    }
                    Text(
                        text = item.price,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF212529)
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun MerchantScreenLoadedPreview() {
    EShoppingTheme {
        MerchantScreen(isLoadingInitial = false)
    }
}

@Composable
@Preview
fun MerchantScreenLoadingPreview() {
    EShoppingTheme {
        MerchantScreen(isLoadingInitial = true)
    }
}
