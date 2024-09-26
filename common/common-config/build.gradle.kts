dependencies {
    implementation(project(":common:common-utils"))
    implementation(project(":common:common-api"))
    api(project(":model"))

    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
}