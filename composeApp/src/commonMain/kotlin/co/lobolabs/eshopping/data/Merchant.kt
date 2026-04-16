package co.lobolabs.eshopping.data

data class Merchant(
    val picture: String?,
    val name: String,
) {
    companion object {
        val items = listOf(
            Merchant(
                picture = null,
                name = "Seu Lobo"
            )
        )
    }
}
