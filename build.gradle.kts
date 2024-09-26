// 插件
plugins {
    alias(libs.plugins.kotlinSpringPlugin)
    alias(libs.plugins.kotlinJvmPlugin)
    alias(libs.plugins.springBootPlugin)
    alias(libs.plugins.springDependencyManagementPlugin)
}

allprojects {
    group = "org.atxiong.cloud"
    version = "0.0.1-SNAPSHOT"

    // 需要 用到 rootProject
    val libs = rootProject.libs

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    repositories {
        mavenLocal()
        maven("https://maven.aliyun.com/repository/public")
        mavenCentral()
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

   // Spring BOM
    dependencies {
        libs.bundles.springCloudBom.get().forEach {
            add("api", platform(it))
        }
    }

    subprojects {
        tasks.withType<Test> {
            useJUnitPlatform()
        }

        // 应用spring插件
        apply(plugin = libs.plugins.springBootPlugin.get().pluginId)
        apply(plugin = libs.plugins.kotlinJvmPlugin.get().pluginId)
        apply(plugin = libs.plugins.springDependencyManagementPlugin.get().pluginId)
        apply(plugin = libs.plugins.kotlinSpringPlugin.get().pluginId)
    }
}



