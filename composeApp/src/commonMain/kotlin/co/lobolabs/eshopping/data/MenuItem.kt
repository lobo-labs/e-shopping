package co.lobolabs.eshopping.data

data class MenuItem(
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String
) {
    companion object {
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
    }
}
