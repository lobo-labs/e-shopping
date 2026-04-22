package co.lobolabs.eshopping.presentation.merchant.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.lobolabs.eshopping.presentation.ui.extension.shimmerLoadingAnimation
import eshopping.composeapp.generated.resources.Res
import eshopping.composeapp.generated.resources.ic_delivery
import org.jetbrains.compose.resources.painterResource

@Composable
fun MerchantDeliveryOptions(
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