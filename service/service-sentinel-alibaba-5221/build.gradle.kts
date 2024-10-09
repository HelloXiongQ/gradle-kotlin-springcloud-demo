dependencies {
    // 使用micrometer
    implementation(libs.bundles.micrometer)
    // nacos
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery")
    implementation("com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel")
    implementation("com.alibaba.csp:sentinel-datasource-nacos")  // sentinel 配置持久化
}