package org.kosql

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*
import java.util.logging.Logger
import javax.sql.DataSource

/**
 * A facade over Java's normal JDBC APIs providing greatly simplified resource management and result set handling.
 *
 * @author Marcel Overdijk
 */
class Sql private constructor() : AutoCloseable {

    private val logger = Logger.getLogger(javaClass.name)

    var dataSource: DataSource? = null
        private set

    var connection: Connection? = null
        private set

    constructor(dataSource: DataSource) : this() {
        this.dataSource = dataSource
    }

    constructor(connection: Connection) : this() {
        this.connection = connection
    }

    constructor(parent: Sql) : this() {
        this.dataSource = parent.dataSource
        this.connection = parent.connection
    }

    companion object {

        @Throws(SQLException::class, ClassNotFoundException::class)
        fun newInstance(
            url: String,
            username: String? = null,
            password: String? = null,
            driverClassName: String? = null,
        ): Sql {
            driverClassName?.let { loadDriver(it) }
            val connection = DriverManager.getConnection(url, username, password)
            return Sql(connection)
        }

        @Throws(SQLException::class, ClassNotFoundException::class)
        fun withInstance(
            url: String,
            username: String? = null,
            password: String? = null,
            driverClassName: String? = null,
            body: (sql: Sql) -> Unit,
        ) {
            newInstance(url, username, password, driverClassName).use { sql -> body(sql) }
        }

        @Throws(SQLException::class, ClassNotFoundException::class)
        fun newInstance(
            url: String,
            properties: Properties,
            driverClassName: String? = null,
        ): Sql {
            driverClassName?.let { loadDriver(it) }
            val connection = DriverManager.getConnection(url, properties)
            return Sql(connection)
        }

        @Throws(SQLException::class, ClassNotFoundException::class)
        fun withInstance(
            url: String,
            properties: Properties,
            driverClassName: String? = null,
            body: (sql: Sql) -> Unit,
        ) {
            newInstance(url, properties, driverClassName).use { sql -> body(sql) }
        }

        @Throws(ClassNotFoundException::class)
        fun loadDriver(driverClassName: String) {
            TODO("Not yet implemented")
        }
    }

    override fun close() {
        // TODO: clear caches.
        connection?.let {
            try {
                it.close()
            } catch (e: SQLException) {
                logger.finest("Caught exception closing connection: ${e.message}")
            }
        }
    }

    @Throws(SQLException::class)
    fun execute(sql: String): Boolean {
        TODO("Not yet implemented")
    }
}
