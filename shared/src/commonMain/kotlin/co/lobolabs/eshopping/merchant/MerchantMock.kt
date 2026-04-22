package co.lobolabs.eshopping.merchant

import co.lobolabs.eshopping.schedule.MerchantSchedule
import co.lobolabs.eshopping.schedule.MerchantScheduleTimes

object MerchantMock {
    val schedules = listOf(
        MerchantSchedule(
            merchantId = 1,
            schedule = listOf(
                MerchantScheduleTimes(
                    dayOfWeek = "Segunda",
                    openTime = "18:00",
                    closeTime = "22:00"
                ),
                MerchantScheduleTimes(
                    dayOfWeek = "Terça",
                    openTime = "18:00",
                    closeTime = "22:00"
                ),
                MerchantScheduleTimes(
                    dayOfWeek = "Quarta",
                    openTime = "18:00",
                    closeTime = "22:00"
                ),
                MerchantScheduleTimes(
                    dayOfWeek = "Quinta",
                    openTime = "18:00",
                    closeTime = "22:00"
                ),
                MerchantScheduleTimes(
                    dayOfWeek = "Sexta",
                    openTime = "18:00",
                    closeTime = "22:00"
                ),
                MerchantScheduleTimes(
                    dayOfWeek = "Sábado",
                    openTime = "18:00",
                    closeTime = "22:00"
                ),
                MerchantScheduleTimes(
                    dayOfWeek = "Domingo",
                    openTime = "18:00",
                    closeTime = "22:00"
                ),
            )
        )
    )

    val merchants = listOf(
        MerchantResponse(
            id = 1,
            username = "seulobo",
            name = "Seu Lobo",
            email = "seulobo@gmail.com",
            phone = "84991486748",
            categories = listOf(
                "BURGER",
                "CUSCUZ"
            ),
            address = "Rua dos Bobos, 0",
            schedule = schedules.find { it.merchantId == 1 }?.schedule.orEmpty(),
            scheduleStatus = "Fechado",
            isOpen = false
        )
    )


}