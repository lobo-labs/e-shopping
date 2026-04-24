package co.lobolabs.eshopping.presentation.merchant.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import co.lobolabs.eshopping.data.MerchantSchedule
import co.lobolabs.eshopping.merchant.MerchantMock
import co.lobolabs.eshopping.merchant.MerchantResponse
import co.lobolabs.eshopping.presentation.ui.EShoppingTheme
import eshopping.composeapp.generated.resources.Res
import eshopping.composeapp.generated.resources.ic_location
import eshopping.composeapp.generated.resources.ic_whatsapp
import org.jetbrains.compose.resources.painterResource

@Composable
fun MerchantAboutPage(
    merchant: MerchantResponse?,
    schedule: MerchantSchedule
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(color = Color.LightGray.copy(alpha = 0.6f))
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = merchant?.name.orEmpty(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Status Tag
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(Color(0xFFE8F5E9))
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = schedule.statusLabel,
                fontSize = 12.sp,
                color = Color(0xFF4CAF50),
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Endereço Section
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Endereço",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(Res.drawable.ic_location),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = merchant?.address.orEmpty(),
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 20.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Contatos",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // WhatsApp Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFFEEEEEE),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF5F5F5)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_whatsapp),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.Black
                    )
                }

                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "(84) 98170-6656",
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    EShoppingTheme {
        MerchantAboutPage(
            merchant = MerchantMock.merchants.first(),
            schedule = MerchantSchedule.defaultSchedule.last()
        )
    }
}

