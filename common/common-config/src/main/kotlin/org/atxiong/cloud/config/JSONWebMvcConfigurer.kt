package org.atxiong.cloud.config

import com.alibaba.fastjson2.JSONReader
import com.alibaba.fastjson2.JSONWriter
import com.alibaba.fastjson2.support.config.FastJsonConfig
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter
import com.alibaba.fastjson2.support.spring6.webservlet.view.FastJsonJsonView
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.nio.charset.StandardCharsets

/**
 * fastjson 配置类
 */
@Configuration
class JSONWebMvcConfigurer: WebMvcConfigurer {

    @Bean
    fun fastJsonConfig(): FastJsonConfig = FastJsonConfig().apply {
        dateFormat = "yyyy-MM-dd HH:mm:ss"
        setWriterFeatures(JSONWriter.Feature.PrettyFormat)
        setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean)
    }

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) = FastJsonHttpMessageConverter().let {
        it.defaultCharset = StandardCharsets.UTF_8
        it.fastJsonConfig = fastJsonConfig()
        it.supportedMediaTypes = listOf(MediaType.APPLICATION_JSON)
        converters.add(0, it)
    }

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        registry.enableContentNegotiation(FastJsonJsonView().apply {
            fastJsonConfig = fastJsonConfig()
        })
    }

}