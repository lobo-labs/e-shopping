package co.lobolabs.eshopping.presentation.merchant.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.lobolabs.eshopping.data.MenuItem
import eshopping.composeapp.generated.resources.Res
import eshopping.composeapp.generated.resources.ic_close
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailBottomSheet(
    item: MenuItem,
    onDismissRequest: () -> Unit,
    onAddToCart: (MenuItem, Int, String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var quantity by remember { mutableStateOf(1) }
    var comment by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        dragHandle = null,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Header with Image and Close Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .background(Color(0xFFF0F0F0))
            ) {
                // Placeholder for product image
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray.copy(alpha = 0.3f))
                )

                // Close Button
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.5f))
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = onDismissRequest
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Fechar",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = item.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212529)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = item.price,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212529)
                )
            }

            // Additional Comment Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF8F9FA))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Algum comentário adicional?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF212529)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                BasicTextField(
                    value = comment,
                    onValueChange = { comment = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .border(1.dp, Color(0xFFDEE2E6), RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                    decorationBox = { innerTextField ->
                        if (comment.isEmpty()) {
                            Text(
                                text = "",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Bar
            HorizontalDivider(color = Color(0xFFEEEEEE))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Quantity Selector
                Row(
                    modifier = Modifier
                        .height(48.dp)
                        .border(1.dp, Color(0xFFDEE2E6), RoundedCornerShape(8.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { if (quantity > 1) quantity-- }) {
                        Text("-", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                    Text(
                        text = quantity.toString(),
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = { quantity++ }) {
                        Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }

                // Add Button
                Button(
                    onClick = { onAddToCart(item, quantity, comment) },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF212529)
                    )
                ) {
                    Text(
                        "Adicionar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
