package co.lobolabs.eshopping.data

enum class PaymentType(
    val text: String
) {
    CASH("Dinheiro"),
    CREDIT("Crédito"),
    DEBIT("Débito")
}
