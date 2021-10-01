package tech.nerostarx.databases

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object MainDataBase {
    private val xconfig = HikariConfig().apply {
        jdbcUrl         = "jdbc:postgresql://localhost:5432/milky_store"
        driverClassName = "org.postgresql.Driver"
        username        = "nerostarx"
        password        = "*******"
        maximumPoolSize = 10
    }

    private val config = HikariConfig().apply {
        jdbcUrl         = "jdbc:postgresql://ec2-3-227-44-84.compute-1.amazonaws.com:5432/db9rsc5tedvdhn"
        driverClassName = "org.postgresql.Driver"
        username        = "rzxevgxfwselav"
        password        = "877e1c0be866b04be373cafe50c5f6db7e8f0d290356f53a6bb505f89461f0d5"
        maximumPoolSize = 10
    }

    private val dataSource = HikariDataSource(config)

    val dbInstance by lazy {
        Database.connect(dataSource)
    }
}