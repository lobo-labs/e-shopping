package co.lobolabs.eshopping.features.merchant.domain.route

import co.lobolabs.eshopping.features.merchant.data.repository.MerchantRepositoryImpl
import co.lobolabs.eshopping.features.merchant.domain.repository.MerchantRepository
import co.lobolabs.eshopping.merchant.MerchantResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import kotlinx.coroutines.delay

fun Route.getMerchantRoute() {

    val repository: MerchantRepository = MerchantRepositoryImpl()

    get("/api/merchant/{id}") {
        val merchantId = call.parameters["id"]?.toIntOrNull()
            ?: return@get call.respond(HttpStatusCode.BadRequest)

        val merchant: MerchantResponse? = runCatching {
            delay(2000L)
            repository.getMerchantById(merchantId)
        }.onFailure {
            return@get call.respond(HttpStatusCode.BadRequest)
        }.getOrNull()

        if (merchant != null) {
            call.respond(merchant)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}
