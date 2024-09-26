subprojects {
    dependencies {
        implementation(rootProject.libs.mybatisPlus)
        implementation(rootProject.libs.swagger3)
        // web开发依赖
        api(rootProject.libs.bundles.common)

        if (!name.contains("common-utils")) api(project(":model"))
    }
}