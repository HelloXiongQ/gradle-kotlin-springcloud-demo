plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "SpringCloudStudy"

// 项目模块
include("common", "model", "service", "mybatisPlus_generator")

include(
    "service:service-payment-8001",
    "service:service-order-9001",
    "service:service-order-restClient-9001",
    "service:service-gateway-9527",
    "service:service-order-alibaba-7001",
    "service:service-payment-alibaba-6001",
)

include("common:common-config", "common:common-utils")
include("common:common-api")
findProject(":common:common-api")?.name = "common-api"
