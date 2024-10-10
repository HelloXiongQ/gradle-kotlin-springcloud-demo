plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "SpringCloudStudy"

// 项目模块
include("common", "model", "service", "mybatisPlus_generator", "gateway-9527", "alibaba-gateway-9527")

include(
    "service:service-payment-8001",
    "service:service-order-9001",
    "service:service-order-restClient-9001",
    "service:service-order-alibaba-7001",
    "service:service-payment-alibaba-6001",
    "service:service-sentinel-alibaba-5221",
    "service:service-order-seata-2001",
    "service:service-account-seata-2002",
    "service:service-storage-seata-2003"
)

include("common:common-config", "common:common-utils")
include("common:common-api")
findProject(":common:common-api")?.name = "common-api"
