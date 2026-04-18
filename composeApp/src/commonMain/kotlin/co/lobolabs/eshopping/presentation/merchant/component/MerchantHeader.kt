package co.lobolabs.eshopping.presentation.merchant.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.lobolabs.eshopping.data.Merchant
import co.lobolabs.eshopping.presentation.merchant.shimmerLoadingAnimation
import eshopping.composeapp.generated.resources.Res
import eshopping.composeapp.generated.resources.ic_menu
import eshopping.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.painterResource

@Composable
fun MerchantHeader(
    isLoading: Boolean,
    merchant: Merchant,
    onOpenSchedule: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLoading) {
            // ... (shimmer loading remains same)
        } else {
            // ... (image remains same)
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(color = Color.LightGray.copy(alpha = 0.6f))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(4.dp))
                    .clickable { onOpenSchedule() }
            ) {
                Text(
                    text = merchant.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color(0xFF4CAF50))
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Aberto até 14:30",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.Gray
                    )
                }
            }
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerLoadingAnimation()
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shimmerLoadingAnimation()
            )
        } else {
            Icon(
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.DarkGray
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = painterResource(Res.drawable.ic_menu),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.DarkGray
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    MaterialTheme {
        MerchantHeader(
            isLoading = false,
            merchant = Merchant.items.first()
        )
    }
}

@Composable
@Preview
private fun PreviewLoading() {
    MaterialTheme {
        MerchantHeader(
            isLoading = true,
            merchant = Merchant.items.first()
        )
    }
}
