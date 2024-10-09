dependencies {
    // 使用micrometer
    implementation(libs.bundles.micrometer)
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    // nacos
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")

    // sentinel
    implementation("com.alibaba.csp:sentinel-spring-cloud-gateway-adapter:1.8.6")
    implementation("com.alibaba.csp:sentinel-transport-simple-http:1.8.6")

    implementation(kotlin("reflect"))
    implementation(project(":common:common-utils")) {
        // gateway 不需要 spring mvc web 和 mybatisPlus
        with(rootProject.libs) {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-web")
            exclude(group = mybatisPlus.get().group, module = mybatisPlus.get().name)
        }
    }

}