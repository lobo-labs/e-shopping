package co.lobolabs.eshopping.database.entities

import co.lobolabs.eshopping.database.ProductType
import org.ktorm.entity.Entity
import org.ktorm.schema.*

interface Product : Entity<Product> {
    companion object : Entity.Factory<Product>()
    var id: Int
    var storeId: Int
    var name: String
    var description: String
    var type: ProductType
    var active: Boolean
}

object Products : Table<Product>("products") {
    val id = int("id").primaryKey().bindTo { it.id }
    val storeId = int("store_id").bindTo { it.storeId }
    val name = varchar("name").bindTo { it.name }
    val description = varchar("description").bindTo { it.description }
    val type = varchar("type").transform({ ProductType.valueOf(it) }, { it.name }).bindTo { it.type }
    val active = boolean("active").bindTo { it.active }
}

interface ProductVariant : Entity<ProductVariant> {
    companion object : Entity.Factory<ProductVariant>()
    var id: Int
    var productId: Int
    var name: String
    var price: Long
    var active: Boolean
}

object ProductVariants : Table<ProductVariant>("product_variants") {
    val id = int("id").primaryKey().bindTo { it.id }
    val productId = int("product_id").bindTo { it.productId }
    val name = varchar("name").bindTo { it.name }
    val price = long("price").bindTo { it.price }
    val active = boolean("active").bindTo { it.active }
}

interface ProductAddonGroup : Entity<ProductAddonGroup> {
    companion object : Entity.Factory<ProductAddonGroup>()
    var id: Int
    var productId: Int
    var name: String
    var minSelections: Int
    var maxSelections: Int
    var isRequired: Boolean
}

object ProductAddonGroups : Table<ProductAddonGroup>("product_addon_groups") {
    val id = int("id").primaryKey().bindTo { it.id }
    val productId = int("product_id").bindTo { it.productId }
    val name = varchar("name").bindTo { it.name }
    val minSelections = int("min_selections").bindTo { it.minSelections }
    val maxSelections = int("max_selections").bindTo { it.maxSelections }
    val isRequired = boolean("is_required").bindTo { it.isRequired }
}

interface ProductAddon : Entity<ProductAddon> {
    companion object : Entity.Factory<ProductAddon>()
    var id: Int
    var groupId: Int
    var name: String
    var price: Long
}

object ProductAddons : Table<ProductAddon>("product_addons") {
    val id = int("id").primaryKey().bindTo { it.id }
    val groupId = int("group_id").bindTo { it.groupId }
    val name = varchar("name").bindTo { it.name }
    val price = long("price").bindTo { it.price }
}
