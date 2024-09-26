package org.atxiong.cloud.config

import org.atxiong.cloud.http.PayApiExchange
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

/**
 * RestClient 代替 OpenFeign
 */
@Configuration
class RestClientConfiguration {

    companion object {
        val commonPath = "http://cloud-gateway-service"
    }

    @Bean
    @LoadBalanced
    fun restClientBuilder(): RestClient.Builder {
        return RestClient.builder()
    }

    @Bean
    fun orderClient(builder: RestClient.Builder): PayApiExchange {
//        val restClient = builder.baseUrl("http://cloud-payment-service").build()
        // 配合网关通用api设置
        val restClient = builder.baseUrl(commonPath).build()
        val adapter = RestClientAdapter.create(restClient)
        val factory = HttpServiceProxyFactory.builderFor(adapter).build()

        return factory.createClient(PayApiExchange::class.java)
    }
}