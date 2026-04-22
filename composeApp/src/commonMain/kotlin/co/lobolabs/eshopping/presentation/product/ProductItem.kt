package co.lobolabs.eshopping.presentation.product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.lobolabs.eshopping.data.MenuItem
import co.lobolabs.eshopping.presentation.ui.extension.shimmerLoadingAnimation

@Composable
fun ProductItem(item: MenuItem?, isLoading: Boolean, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        ),
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