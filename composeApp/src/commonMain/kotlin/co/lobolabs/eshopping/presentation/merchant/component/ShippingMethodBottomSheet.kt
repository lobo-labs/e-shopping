package co.lobolabs.eshopping.presentation.merchant.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.lobolabs.eshopping.presentation.ui.EShoppingTheme
import eshopping.composeapp.generated.resources.Res
import eshopping.composeapp.generated.resources.ic_close
import eshopping.composeapp.generated.resources.ic_delivery
import eshopping.composeapp.generated.resources.ic_location_map
import eshopping.composeapp.generated.resources.ic_pickup
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

enum class OrderType(val title: String, val subtitle: String, val icon: DrawableResource) {
    DELIVERY("Entrega", "A gente leva até você", Res.drawable.ic_delivery),
    PICKUP("Retirada", "Você retira no local", Res.drawable.ic_pickup),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShippingMethodBottomSheet(
    onDismiss: () -> Unit,
    onConfirm: (OrderType) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var selectedType by remember { mutableStateOf(OrderType.DELIVERY) }

    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val headerHeight = 80.dp
    val topPadding = statusBarHeight + headerHeight

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White,
        modifier = Modifier.padding(top = topPadding).fillMaxHeight(),
        dragHandle = null
    ) {
        ShippingMethodPage(
            selectedType,
            onDismiss,
            onConfirm
        )
    }
}

@Composable
fun ShippingMethodPage(
    orderType: OrderType,
    onDismiss: () -> Unit,
    onConfirm: (OrderType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(Color(0xFFF5F5F5), CircleShape)
                    .size(32.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_close),
                    contentDescription = "Fechar",
                    modifier = Modifier.size(16.dp),
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Map Icon Placeholder
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFFDF2E2)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_location_map),
                contentDescription = null,
                modifier = Modifier.size(60.dp),
                tint = Color.Unspecified
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Como você quer receber seu pedido?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(OrderType.entries) {
                ShippingMethodOption(
                    title = it.title,
                    description = it.subtitle,
                    icon = it.icon,
                    isSelected = orderType == it,
                    onClick = { onConfirm(it) }
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { onConfirm(orderType) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1A1A1A)
            )
        ) {
            Text(
                text = "Confirmar pedido",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun ShippingMethodOption(
    title: String,
    description: String,
    icon: DrawableResource,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isSelected) Color.Black else Color(0xFFEEEEEE),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFF8F8F8), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = description,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        RadioButton(
            selected = isSelected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Black,
                unselectedColor = Color.LightGray
            )
        )
    }
}

@Composable
@Preview
private fun Preview() {
    EShoppingTheme {
        ShippingMethodPage(
            orderType = OrderType.DELIVERY,
            onDismiss = {},
            onConfirm = {}
        )
    }
}
