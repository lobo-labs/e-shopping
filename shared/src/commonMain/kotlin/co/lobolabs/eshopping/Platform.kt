package co.lobolabs.eshopping

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform