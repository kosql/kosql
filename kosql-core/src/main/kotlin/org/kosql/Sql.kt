package org.kosql

import java.sql.SQLException
import java.util.logging.Logger

/**
 * A facade over Java's normal JDBC APIs providing greatly simplified resource management and result set handling.
 *
 * @author Marcel Overdijk
 */
class Sql private constructor() : AutoCloseable {

    private val logger = Logger.getLogger(javaClass.name)

    companion object {
    }

    override fun close() {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun execute(sql: String): Boolean {
        TODO("Not yet implemented")
    }
}
