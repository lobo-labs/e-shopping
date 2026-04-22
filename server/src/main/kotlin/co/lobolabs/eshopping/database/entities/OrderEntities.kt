package co.lobolabs.eshopping.database.entities

import co.lobolabs.eshopping.database.OrderItemType
import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.time.Instant

interface Order : Entity<Order> {
    companion object : Entity.Factory<Order>()
    var id: Int
    var storeId: Int
    var status: String
    var total: Long
    var createdAt: Instant
    var scheduledAt: Instant?
}

object Orders : Table<Order>("orders") {
    val id = int("id").primaryKey().bindTo { it.id }
    val storeId = int("store_id").bindTo { it.storeId }
    val status = varchar("status").bindTo { it.status }
    val total = long("total").bindTo { it.total }
    val createdAt = timestamp("created_at").bindTo { it.createdAt }
    val scheduledAt = timestamp("scheduled_at").bindTo { it.scheduledAt }
}

interface OrderItem : Entity<OrderItem> {
    companion object : Entity.Factory<OrderItem>()
    var id: Int
    var orderId: Int
    var productId: Int?
    var variantId: Int?
    var quantity: Int
    var type: OrderItemType
    var notes: String?
}

object OrderItems : Table<OrderItem>("order_items") {
    val id = int("id").primaryKey().bindTo { it.id }
    val orderId = int("order_id").bindTo { it.orderId }
    val productId = int("product_id").bindTo { it.productId }
    val variantId = int("variant_id").bindTo { it.variantId }
    val quantity = int("quantity").bindTo { it.quantity }
    val type = varchar("type").transform({ OrderItemType.valueOf(it) }, { it.name }).bindTo { it.type }
    val notes = varchar("notes").bindTo { it.notes }
}

interface OrderItemAddon : Entity<OrderItemAddon> {
    companion object : Entity.Factory<OrderItemAddon>()
    var id: Int
    var orderItemId: Int
    var addonId: Int
    var quantity: Int
}

object OrderItemAddons : Table<OrderItemAddon>("order_item_addons") {
    val id = int("id").primaryKey().bindTo { it.id }
    val orderItemId = int("order_item_id").bindTo { it.orderItemId }
    val addonId = int("addon_id").bindTo { it.addonId }
    val quantity = int("quantity").bindTo { it.quantity }
}

interface OrderItemPart : Entity<OrderItemPart> {
    companion object : Entity.Factory<OrderItemPart>()
    var id: Int
    var orderItemId: Int
    var productId: Int
    var fraction: Double
}

object OrderItemParts : Table<OrderItemPart>("order_item_parts") {
    val id = int("id").primaryKey().bindTo { it.id }
    val orderItemId = int("order_item_id").bindTo { it.orderItemId }
    val productId = int("product_id").bindTo { it.productId }
    val fraction = double("fraction").bindTo { it.fraction }
}
