import org.atxiong.cloud.plugins.importSubProjects

// 需要忽略配置的子项目
val ignoreProject = listOf("service-gateway-9527")

subprojects {
    dependencies {
        // global service dependency
        implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
        implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery")

        if (name in ignoreProject) return@dependencies

        // 导入本地依赖（common 中包含 web开发依赖）
        implementation(project(":model"))
        importSubProjects(findProject(":common"))

        // web开发依赖
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        implementation("org.springframework.boot:spring-boot-devtools")

        // cloud
        implementation("org.springframework.cloud:spring-cloud-starter-consul-config")
        implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
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