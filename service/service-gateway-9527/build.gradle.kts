dependencies {
    // 使用micrometer
    implementation(libs.bundles.micrometer)
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation(kotlin("reflect"))
    implementation(project(":common:common-utils")) {
        // gateway 不需要 spring mvc web 和 mybatisPlus
        with(rootProject.libs) {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-web")
            exclude(group = mybatisPlus.get().group, module = mybatisPlus.get().name)
        }
    }

}