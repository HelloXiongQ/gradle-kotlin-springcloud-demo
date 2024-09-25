group = "org.atxiong"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(libs.mybatisPlus)
    implementation("com.baomidou:mybatis-plus-generator:3.5.7")
    implementation("org.freemarker:freemarker:2.3.33")
    // mysql驱动
    runtimeOnly("com.mysql:mysql-connector-j")
}