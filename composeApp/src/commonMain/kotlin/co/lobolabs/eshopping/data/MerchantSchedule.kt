package co.lobolabs.eshopping.data

data class MerchantSchedule(
    val dayOfWeek: String,
    val openingTime: String?,
    val closingTime: String?,
    val isClosed: Boolean = false
) {
    val displayHours: String
        get() = if (isClosed || openingTime == null || closingTime == null) {
            "Fechado"
        } else {
            "$openingTime às $closingTime"
        }

    companion object {
        val defaultSchedule = listOf(
            MerchantSchedule("Segunda-feira", null, null, isClosed = true),
            MerchantSchedule("Terça-feira", "11:00", "14:30"),
            MerchantSchedule("Quarta-feira", "11:00", "14:30"),
            MerchantSchedule("Quinta-feira", "11:00", "14:30"),
            MerchantSchedule("Sexta-feira", "11:00", "14:30"),
            MerchantSchedule("Sábado", "11:00", "14:30"),
            MerchantSchedule("Domingo", "11:00", "14:30")
        )
    }
}
