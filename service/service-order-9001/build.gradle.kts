dependencies {
    // 断路器依赖
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    // 熔断（隔离舱模式）
    implementation("io.github.resilience4j:resilience4j-bulkhead")
    // 限流
    implementation("io.github.resilience4j:resilience4j-ratelimiter")

    //    consul
    implementation("org.springframework.cloud:spring-cloud-starter-consul-config")
    implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")

    // 使用micrometer
    implementation(libs.bundles.micrometer)
}