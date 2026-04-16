package co.lobolabs.eshopping.presentation.merchant.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.lobolabs.eshopping.data.PaymentType
import eshopping.composeapp.generated.resources.Res
import eshopping.composeapp.generated.resources.ic_card
import eshopping.composeapp.generated.resources.ic_cash
import org.jetbrains.compose.resources.painterResource

@Composable
fun MerchantPaymentPage(
    payments: List<PaymentType> = PaymentType.entries
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        payments.forEach { payment ->
            PaymentItem(payment)
        }
    }
}

@Composable
fun PaymentItem(payment: PaymentType) {
    val (icon, iconColor) = when (payment) {
        PaymentType.CASH -> Res.drawable.ic_cash to Color(0xFF4CAF50)
        else -> Res.drawable.ic_card to Color.Gray
    }

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
                .background(Color(0xFFF5F5F5), RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = iconColor
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = payment.text,
            fontSize = 14.sp,
            color = Color(0xFF333333),
            fontWeight = FontWeight.Medium
        )
    }
}
