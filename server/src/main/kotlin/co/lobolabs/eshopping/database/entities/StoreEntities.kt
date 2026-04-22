package co.lobolabs.eshopping.database.entities

import co.lobolabs.eshopping.database.PricingStrategy
import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.time.LocalDate
import java.time.LocalTime

interface Store : Entity<Store> {
    companion object : Entity.Factory<Store>()
    var id: Int
    var name: String
    var active: Boolean
    var acceptsScheduledOrders: Boolean
    var pricingStrategy: PricingStrategy
}

object Stores : Table<Store>("stores") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val active = boolean("active").bindTo { it.active }
    val acceptsScheduledOrders = boolean("accepts_scheduled_orders").bindTo { it.acceptsScheduledOrders }
    val pricingStrategy = varchar("pricing_strategy").transform({ PricingStrategy.valueOf(it) }, { it.name }).bindTo { it.pricingStrategy }
}

interface StoreHour : Entity<StoreHour> {
    companion object : Entity.Factory<StoreHour>()
    var id: Int
    var storeId: Int
    var dayOfWeek: Int
    var openTime: LocalTime
    var closeTime: LocalTime
}

object StoreHours : Table<StoreHour>("store_hours") {
    val id = int("id").primaryKey().bindTo { it.id }
    val storeId = int("store_id").bindTo { it.storeId }
    val dayOfWeek = int("day_of_week").bindTo { it.dayOfWeek }
    val openTime = time("open_time").bindTo { it.openTime }
    val closeTime = time("close_time").bindTo { it.closeTime }
}

interface StoreSpecialHour : Entity<StoreSpecialHour> {
    companion object : Entity.Factory<StoreSpecialHour>()
    var id: Int
    var storeId: Int
    var date: LocalDate
    var isClosed: Boolean
    var openTime: LocalTime?
    var closeTime: LocalTime?
}

object StoreSpecialHours : Table<StoreSpecialHour>("store_special_hours") {
    val id = int("id").primaryKey().bindTo { it.id }
    val storeId = int("store_id").bindTo { it.storeId }
    val date = date("date").bindTo { it.date }
    val isClosed = boolean("is_closed").bindTo { it.isClosed }
    val openTime = time("open_time").bindTo { it.openTime }
    val closeTime = time("close_time").bindTo { it.closeTime }
}
