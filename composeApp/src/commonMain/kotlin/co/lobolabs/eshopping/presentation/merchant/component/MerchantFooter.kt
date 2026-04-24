package co.lobolabs.eshopping.presentation.merchant.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.lobolabs.eshopping.merchant.MerchantResponse

@Composable
fun MerchantFooter(
    merchant: MerchantResponse?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Logo Placeholder (Since we don't have the TUNA logo)
        Box(
            modifier = Modifier
                .size(120.dp, 40.dp)
                .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = merchant?.name.orEmpty(),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color(0xFF4A4A4A)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "TUNA COZINHA DO MAR LTDA",
            fontSize = 12.sp,
            color = Color.DarkGray
        )

        Text(
            text = "60.572.751/0001-04",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            Text(
                text = "Termos e Políticas",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Política de Privacidade",
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Acompanhe nossas redes sociais",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Instagram Placeholder (No icon in resources yet)
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.LightGray.copy(alpha = 0.3f), RoundedCornerShape(4.dp))
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Fale com a gente",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Telefone: (84) 98170-6656",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "WhatsApp: (84) 98170-6656",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        HorizontalDivider(color = Color(0xFFEEEEEE))
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "© 2024 DeliveryApp - Todos os direitos reservados",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Powered by Neemo",
                fontSize = 10.sp,
                color = Color.Gray
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Delivery App Logo Placeholder
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(Color.Black, RoundedCornerShape(4.dp))
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "DELIVERY APP",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(64.dp))
    }
}
