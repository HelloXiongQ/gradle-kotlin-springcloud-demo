dependencies {
    // 使用micrometer
    implementation(libs.bundles.micrometer)
    //    consul
    implementation("org.springframework.cloud:spring-cloud-starter-consul-config")
    implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")
}