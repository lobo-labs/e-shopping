package co.lobolabs.eshopping

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eshopping.composeapp.generated.resources.Res
import eshopping.composeapp.generated.resources.ic_delivery
import eshopping.composeapp.generated.resources.ic_menu
import eshopping.composeapp.generated.resources.ic_search
import org.jetbrains.compose.resources.painterResource

data class MenuItem(
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String
)

@Composable
@Preview
fun TunaScreen() {
    val items = listOf(
        MenuItem(
            "Poke Atum com Shitake",
            "Arroz de sushi, aioli citrico, tartar de atum, Shitake, sunomono e tomate cereja",
            "R$ 42,00",
            ""
        ),
        MenuItem(
            "Poke Camarao Crocante",
            "Arroz de suhsi, aioli citrico, camarao empanado, tare, gergelim, cebolinha, sunomo...",
            "R$ 37,00",
            ""
        ),
        MenuItem(
            "Poke Peixe Crocante",
            "Arroz de sushi, aioli citrico, peixe empanado...",
            "R$ 39,00",
            ""
        )
    )

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Header()
            DeliveryOptions()
            CategoryTabs()

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 200.dp)
            ) {
                item {
                    Text(
                        text = "POKE",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
                items(items) { item ->
                    ProductItem(item)
                }
            }
        }

        // CookieConsent(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.background(
                color = Color(0xFFE0E0E0),
                shape = RoundedCornerShape(14.dp)
            ).size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Seu Lobo",
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

@Composable
fun DeliveryOptions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card(
            modifier = Modifier.weight(1f).height(56.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFDEE2E6)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_delivery),
                    contentDescription = null,
                    //modifier = Modifier.height(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Entrega", fontSize = 14.sp, modifier = Modifier.weight(1f))
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Gray
                )
            }
        }
        Card(
            modifier = Modifier.weight(1f).height(56.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color(0xFFDEE2E6)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Hoje", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text("30 - 40 min", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun CategoryTabs() {
    val categories = listOf("POKE", "ALMOÇO EXECUTIVO DELIVERY", "ACOM")
    ScrollableTabRow(
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
        categories.forEachIndexed { index, title ->
            Tab(
                selected = index == 0,
                onClick = {},
                text = {
                    Text(
                        text = title,
                        fontSize = 13.sp,
                        fontWeight = if (index == 0) FontWeight.Bold else FontWeight.Normal,
                        color = if (index == 0) Color.Black else Color.Gray
                    )
                }
            )
        }
    }
}

@Composable
fun ProductItem(item: MenuItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(0.5.dp, Color(0xFFEEEEEE))
    ) {
        Row(modifier = Modifier.height(140.dp)) {
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .fillMaxHeight()
                    .background(Color(0xFFF0F0F0))
            ) {
                // Placeholder image color
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

@Composable
fun CookieConsent(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 12.dp,
        border = BorderStroke(1.dp, Color(0xFFEEEEEE))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                Text("🍪", fontSize = 16.sp, modifier = Modifier.padding(top = 2.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Utilizamos cookies para melhorar sua experiência. Ao continuar navegando, você concorda com nossa Política de Privacidade e Política de Cookies.",
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = Color(0xFF495057)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth().clickable { },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Personalizar", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Icon(Icons.Default.KeyboardArrowDown, null, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF212529)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Aceitar Todos", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}
