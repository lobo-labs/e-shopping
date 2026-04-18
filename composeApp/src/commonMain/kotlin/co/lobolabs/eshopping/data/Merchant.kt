package co.lobolabs.eshopping.data

data class Merchant(
    val picture: String?,
    val name: String,
    val phone: String,
    val address: String,
    val isOpen: Boolean = true,
) {
    companion object {
        val items = listOf(
            Merchant(
                picture = null,
                name = "Seu Lobo",
                phone = "(84) 99148-6748",
                address = "Rua Bom Jesus, 14, Belo Horizonte - Mossoró, RN, 59604-453",
                isOpen = true
            )
        )
    }
}
