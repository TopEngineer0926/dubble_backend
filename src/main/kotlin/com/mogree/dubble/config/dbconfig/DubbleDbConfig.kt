package com.mogree.dubble.config.dbconfig

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@PropertySource("application.yml")
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = ["com.mogree.dubble.storage.repository", "com.mogree.jwt.cache.database"])
class DubbleDbConfig {

    @Autowired
    lateinit var env: Environment

    @Primary
    @Bean(name = ["dataSource"])
    @ConfigurationProperties(prefix = "spring.datasource")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Primary
    @Bean(name = ["entityManagerFactory"])
    @ConfigurationProperties(prefix = "spring.datasource")
    fun entityManagerFactory(
            builder: EntityManagerFactoryBuilder,
            @Qualifier("dataSource") dataSource: DataSource?
    ): LocalContainerEntityManagerFactoryBean {
        //properties are set in application.yml file
        val properties: MutableMap<String, Any?> = HashMap()
        val p1 = "hibernate.hbm2ddl.auto" //this property defines the update behaviour for the test database

        //we use env so that we can use the name as a key
        properties[p1] = env.getProperty(p1)

        return builder
                .dataSource(dataSource)
                .packages("com.mogree.dubble.entity", "com.mogree.jwt.cache.database")
                .persistenceUnit("database")
                .properties(properties)
                .build()
    }

    @Primary
    @Bean(name = ["transactionManager"])
    fun transactionManager(
            @Qualifier("entityManagerFactory") entityManagerFactory: EntityManagerFactory?
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory!!)
    }
}