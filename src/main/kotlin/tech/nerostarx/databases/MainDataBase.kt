package tech.nerostarx.databases

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object MainDataBase {
    private val config = HikariConfig().apply {
        jdbcUrl         = "jdbc:postgresql://localhost:5432/milky_store"
        driverClassName = "org.postgresql.Driver"
        username        = "nerostarx"
        password        = "*******"
        maximumPoolSize = 10
    }

    private val dataSource = HikariDataSource(config)

    val dbInstance by lazy {
        Database.connect(dataSource)
    }
}