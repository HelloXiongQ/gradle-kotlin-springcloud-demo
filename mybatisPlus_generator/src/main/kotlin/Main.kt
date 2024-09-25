package org.atxiong

import com.baomidou.mybatisplus.generator.FastAutoGenerator
import com.baomidou.mybatisplus.generator.config.DataSourceConfig
import com.baomidou.mybatisplus.generator.config.OutputFile
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery
import com.baomidou.mybatisplus.generator.config.rules.DateType
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import com.baomidou.mybatisplus.generator.query.SQLQuery
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.support.PropertiesLoaderUtils
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*

object Config {
    // 从 private.properties 中读取 私有 数据库配置
    init {
        val dir = "${System.getProperties().getProperty("user.dir")}${File.separator}private.properties"

        FileInputStream(dir).use { stream ->
            Properties().apply {
                load(stream)
                dataSourceConfig = DataSourceConfig.Builder(
                    getProperty("db.url"),
                    getProperty("db.username"),
                    getProperty("db.password")
                )
            }
        }
    }

    var dataSourceConfig: DataSourceConfig.Builder

    val outDir = "D:\\codeWorkSpace\\java\\SpringCloudStudy\\mybatisPlus_generator\\src\\main\\resources\\generator"
}

fun main() {

    // MYSQL 示例 切换至SQL查询方式,需要指定好 dbQuery 与 typeConvert 来生成
    FastAutoGenerator.create(Config.dataSourceConfig)
        .globalConfig { builder ->
            builder
                .author("xkq")
                .outputDir(Config.outDir)
                .dateType(DateType.TIME_PACK)
                .enableSwagger()
                .enableKotlin()
        }
        .dataSourceConfig { builder ->
            builder.databaseQueryClass(SQLQuery::class.java)
                .typeConvert(MySqlTypeConvert())
                .dbQuery(MySqlQuery())
        }
        .packageConfig { builder ->
            builder.parent("org.atxiong.springcloud") // 设置父包名
//                .moduleName("") // 设置父包模块名
                .pathInfo(Collections.singletonMap(OutputFile.xml, Config.outDir)) // 设置mapperXml生成路径
        }
        .strategyConfig { builder ->
            builder.addInclude("t_pay") // 设置需要生成的表名
                .addTablePrefix("t_", "c_") // 设置过滤表前缀
        }
        .templateEngine(FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
        .execute();
}