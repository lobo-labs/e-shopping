package co.lobolabs.eshopping.presentation.merchant.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.lobolabs.eshopping.data.MenuCategory
import co.lobolabs.eshopping.presentation.merchant.shimmerLoadingAnimation
import co.lobolabs.eshopping.presentation.ui.EShoppingTheme

@Composable
fun MerchantCategoryTabs(
    isLoading: Boolean,
    categories: List<MenuCategory>
) {
    if (isLoading) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(20.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .shimmerLoadingAnimation()
                )
            }
        }
    } else {
        ScrollableTabRow(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
            selectedTabIndex = 0,
            containerColor = Color.White,
            contentColor = Color.Black,
            edgePadding = 16.dp,
            divider = {},
            indicator = { tabPositions ->
                if (tabPositions.isNotEmpty()) {
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[0]),
                        color = Color.Black,
                        height = 2.dp
                    )
                }
            }
        ) {
            categories.forEachIndexed { index, category ->
                Tab(
                    selected = index == 0,
                    onClick = {},
                    text = {
                        Text(
                            text = category.title,
                            fontSize = 13.sp,
                            fontWeight = if (index == 0) FontWeight.Bold else FontWeight.Normal,
                            color = if (index == 0) Color.Black else Color.Gray
                        )
                    }
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    EShoppingTheme {
        MerchantCategoryTabs(
            isLoading = false,
            categories = MenuCategory.items
        )
    }
}

@Composable
@Preview
private fun PreviewLoading() {
    EShoppingTheme {
        MerchantCategoryTabs(
            isLoading = true,
            categories = MenuCategory.items
        )
    }
}
