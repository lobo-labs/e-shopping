plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)
    application
}

group = "co.lobolabs.eshopping"
version = "1.0.0"
application {
    mainClass.set("co.lobolabs.eshopping.ApplicationKt")
    
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    implementation(libs.ktor.serverContentNegotiation)
    implementation(libs.ktor.serializationJson)
    
    implementation(libs.ktorm.core)
    implementation(libs.ktorm.support.postgresql)
    implementation(libs.hikari)
    implementation(libs.postgresql)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
}

val copyWebDist = tasks.register<Copy>("copyWebDist") {
    // Depende da task de build do Wasm (ou JS se preferir)
    dependsOn(":composeApp:wasmJsBrowserDistribution")
    
    from("../composeApp/build/dist/wasmJs/productionExecutable")
    into("src/main/resources/dist")
}

tasks.named("processResources") {
    dependsOn(copyWebDist)
}