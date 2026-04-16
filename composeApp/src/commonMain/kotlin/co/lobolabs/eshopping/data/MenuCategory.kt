package co.lobolabs.eshopping.data

data class MenuCategory(
    val id: String,
    val title: String,
) {
    companion object {
        val items = listOf(
            MenuCategory(
                id = "BURGER",
                title = "Burger Artesanal",
            ),
            MenuCategory(
                id = "SANDWICH",
                title = "Sanduiches",
            ),
            MenuCategory(
                id = "CUSCUZ",
                title = "Cuscuz Recheado",
            ),
            MenuCategory(
                id = "FRIES",
                title = "Batata Frita",
            ),
            MenuCategory(
                id = "DRINK",
                title = "Bebidas",
            )
        )
    }
}
