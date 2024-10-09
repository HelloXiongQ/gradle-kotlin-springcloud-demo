dependencies {
    // 使用micrometer
    implementation(libs.bundles.micrometer)
    // nacos
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config")

    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel")
}