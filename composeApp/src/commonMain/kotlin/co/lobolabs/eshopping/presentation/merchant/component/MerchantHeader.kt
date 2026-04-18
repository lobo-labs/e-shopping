package co.lobolabs.eshopping.presentation.merchant.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
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
    isSearching: Boolean,
    onIsSearchingChange: (Boolean) -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onOpenSchedule: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSearching) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0xFFEEEEEE), RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                BasicTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier.weight(1f),
                    textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                    cursorBrush = SolidColor(Color.Black),
                    decorationBox = { innerTextField ->
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = "Buscar no cardápio",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                )
                Button(
                    onClick = {
                        onIsSearchingChange(false)
                        onSearchQueryChange("")
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.height(32.dp).width(70.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1A1A1A)
                    )
                ) {
                    Text("Cancelar", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        } else {
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .shimmerLoadingAnimation()
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .width(120.dp)
                            .height(16.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .shimmerLoadingAnimation()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(12.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .shimmerLoadingAnimation()
                    )
                }
            } else {
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
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { onOpenSchedule() }
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
                                .background(if (merchant.isOpen) Color(0xFF4CAF50) else Color(0xFFF4271C))
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (merchant.isOpen) "Aberto até 14:30" else "Fechado",
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
        }

        if (!isSearching || isLoading) {
            Spacer(modifier = Modifier.width(16.dp))
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
                    modifier = Modifier.size(24.dp).clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { onIsSearchingChange(true) },
                    tint = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    painter = painterResource(Res.drawable.ic_menu),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp).clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { /* Abrir menu */ },
                    tint = Color.DarkGray
                )
            }
        } else if (isSearching) {
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = painterResource(Res.drawable.ic_menu),
                contentDescription = null,
                modifier = Modifier.size(24.dp).clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { /* Abrir menu */ },
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
            merchant = Merchant.items.first(),
            isSearching = false,
            onIsSearchingChange = {},
            searchQuery = "",
            onSearchQueryChange = {}
        )
    }
}

@Composable
@Preview
private fun PreviewSearching() {
    MaterialTheme {
        MerchantHeader(
            isLoading = false,
            merchant = Merchant.items.first(),
            isSearching = true,
            onIsSearchingChange = {},
            searchQuery = "Poke",
            onSearchQueryChange = {}
        )
    }
}

@Composable
@Preview
private fun PreviewLoading() {
    MaterialTheme {
        MerchantHeader(
            isLoading = true,
            merchant = Merchant.items.first(),
            isSearching = false,
            onIsSearchingChange = {},
            searchQuery = "",
            onSearchQueryChange = {}
        )
    }
}
