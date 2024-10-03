import org.atxiong.cloud.plugins.importSubProjects

// 需要忽略配置的子项目
val ignoreProject = listOf("service-gateway-9527")

subprojects {
    dependencies {
        // 全局服务依赖
        implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")

        // 非全局服务依赖
        if (name in ignoreProject) return@dependencies

        // 导入本地依赖（common 中包含 web开发依赖, model, utils, config）
        importSubProjects(findProject(":common"))

        // web开发依赖
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        implementation("org.springframework.boot:spring-boot-devtools")

        // cloud
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
        implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
        implementation(rootProject.libs.bundles.httpClient)

        // mysql驱动
        runtimeOnly(rootProject.libs.mysql)
        implementation(rootProject.libs.bundles.sql)

        // 测试依赖
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    }
}