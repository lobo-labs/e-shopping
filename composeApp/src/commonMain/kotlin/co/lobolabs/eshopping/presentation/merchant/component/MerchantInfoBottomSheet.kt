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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.lobolabs.eshopping.data.MerchantSchedule
import co.lobolabs.eshopping.presentation.merchant.component.bottomsheet.MerchantSchedulePage
import co.lobolabs.eshopping.presentation.ui.EShoppingTheme

enum class MerchantInfoTabs(val text: String) {
    ABOUT("Sobre"),
    SCHEDULE("Horário"),
    PAYMENT("Pagamentos")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MerchantInfoBottomSheet(
    schedule: List<MerchantSchedule> = MerchantSchedule.defaultSchedule,
    onDismissRequest: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var selectedTab by remember { mutableStateOf(MerchantInfoTabs.SCHEDULE) }

    if (LocalInspectionMode.current) {
        MerchantInfoModalContent(
            selectedTab = selectedTab,
            schedule = schedule,
            onTabSelected = { selectedTab = it },
            onDismissRequest = onDismissRequest
        )
    } else {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            containerColor = Color.White,
            dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .width(40.dp)
                        .height(4.dp)
                        .background(Color(0xFFE0E0E0), RoundedCornerShape(2.dp))
                )
            }
        ) {
            MerchantInfoModalContent(
                selectedTab = selectedTab,
                schedule = schedule,
                onTabSelected = { selectedTab = it },
                onDismissRequest = onDismissRequest
            )
        }
    }
}

@Composable
fun MerchantInfoModalContent(
    selectedTab: MerchantInfoTabs,
    schedule: List<MerchantSchedule>,
    onTabSelected: (MerchantInfoTabs) -> Unit,
    onDismissRequest: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sobre o estabelecimento",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        TabRow(
            selectedTabIndex = selectedTab.ordinal,
            containerColor = Color.White,
            contentColor = Color.Black,
            divider = {
                HorizontalDivider(color = Color(0xFFEEEEEE))
            },
            indicator = { tabs ->
                if (tabs.isNotEmpty()) {
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabs[selectedTab.ordinal]),
                        color = Color.Black,
                        height = 2.dp
                    )
                }
            }
        ) {
            MerchantInfoTabs.entries.forEach { tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = { onTabSelected(tab) },
                    text = {
                        Text(
                            text = tab.text,
                            fontSize = 14.sp,
                            fontWeight = if (selectedTab == tab) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == tab) Color.Black else Color.Gray
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTab) {
            MerchantInfoTabs.SCHEDULE -> MerchantSchedulePage(schedule)
            else -> Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Conteúdo da aba ${selectedTab.text}", color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onDismissRequest,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1A1A1A)
            )
        ) {
            Text(
                text = "Ok",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun MerchantScheduleRow(day: String, hours: String, isHighlighted: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(if (isHighlighted) Color(0xFFF8F8F8) else Color.Transparent)
            .padding(horizontal = 12.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = day,
            fontSize = 15.sp,
            color = Color(0xFF333333)
        )
        Text(
            text = hours,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF333333)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun MerchantInfoBottomSheetPreview() {
    EShoppingTheme {
        MerchantInfoBottomSheet(onDismissRequest = {})
    }
}
