plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
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