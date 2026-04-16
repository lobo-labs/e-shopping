package co.lobolabs.eshopping.presentation.merchant.component.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.lobolabs.eshopping.data.MerchantSchedule
import co.lobolabs.eshopping.presentation.merchant.component.MerchantScheduleRow
import co.lobolabs.eshopping.presentation.ui.EShoppingTheme

@Composable
fun MerchantSchedulePage(
    schedule: List<MerchantSchedule>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        schedule.forEachIndexed { index, item ->
            MerchantScheduleRow(
                day = item.dayOfWeek,
                hours = item.displayHours,
                isHighlighted = index % 2 == 0
            )
        }
    }
}

@Composable
@Preview
private fun Preview() {
    EShoppingTheme {
        MerchantSchedulePage(
            MerchantSchedule.defaultSchedule
        )
    }
}