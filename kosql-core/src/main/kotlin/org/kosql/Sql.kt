package org.kosql

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
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

    var resultSetType = ResultSet.TYPE_FORWARD_ONLY
    var resultSetConcurrency = ResultSet.CONCUR_READ_ONLY
    var resultSetHoldability = -1

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
    fun query(
        sql: String,
        body: (resultSet: ResultSet) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun forEachRow(
        sql: String,
        offset: Int? = 0,
        maxRows: Int? = 0,
        body: (resultSet: KotlinResultSet) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun forEachRow(
        sql: String,
        params: Array<Any>,
        offset: Int? = 0,
        maxRows: Int? = 0,
        body: (resultSet: KotlinResultSet) -> Unit,
    ) {
        forEachRow(sql, params.asList(), offset, maxRows, body)
    }

    @Throws(SQLException::class)
    fun forEachRow(
        sql: String,
        params: List<Any>,
        offset: Int? = 0,
        maxRows: Int? = 0,
        body: (resultSet: KotlinResultSet) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun forEachRow(
        sql: String,
        params: Map<*, *>,
        offset: Int? = 0,
        maxRows: Int? = 0,
        body: (resultSet: KotlinResultSet) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    fun forEachRowIndexed(
        sql: String,
        offset: Int? = 0,
        maxRows: Int? = 0,
        body: (index: Int, resultSet: KotlinResultSet) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    fun forEachRowIndexed(
        sql: String,
        params: Array<Any>,
        offset: Int? = 0,
        maxRows: Int? = 0,
        body: (index: Int, resultSet: KotlinResultSet) -> Unit,
    ) {
        forEachRowIndexed(sql, params.asList(), offset, maxRows, body)
    }

    fun forEachRowIndexed(
        sql: String,
        params: List<Any>,
        offset: Int? = 0,
        maxRows: Int? = 0,
        body: (index: Int, resultSet: KotlinResultSet) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    fun forEachRowIndexed(
        sql: String,
        params: Map<*, *>,
        offset: Int? = 0,
        maxRows: Int? = 0,
        body: (index: Int, resultSet: KotlinResultSet) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun rows(
        sql: String,
        offset: Int? = 0,
        maxRows: Int? = 0,
    ): List<KotlinRowResult> {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun rows(
        sql: String,
        params: Array<Any>,
        offset: Int? = 0,
        maxRows: Int? = 0,
    ): List<KotlinRowResult> {
        return rows(sql, params.asList(), offset, maxRows)
    }

    @Throws(SQLException::class)
    fun rows(
        sql: String,
        params: List<Any>,
        offset: Int? = 0,
        maxRows: Int? = 0,
    ): List<KotlinRowResult> {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun rows(
        sql: String,
        params: Map<*, *>,
        offset: Int? = 0,
        maxRows: Int? = 0,
    ): List<KotlinRowResult> {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class, NoSuchElementException::class)
    fun firstRow(
        sql: String,
    ): KotlinRowResult {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class, NoSuchElementException::class)
    fun firstRow(
        sql: String,
        params: Array<Any>,
    ): KotlinRowResult {
        return firstRow(sql, params.asList())
    }

    @Throws(SQLException::class, NoSuchElementException::class)
    fun firstRow(
        sql: String,
        params: List<Any>,
    ): KotlinRowResult {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class, NoSuchElementException::class)
    fun firstRow(
        sql: String,
        params: Map<*, *>,
    ): KotlinRowResult {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun firstRowOrNull(
        sql: String,
    ): KotlinRowResult? {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun firstRowOrNull(
        sql: String,
        params: Array<Any>,
    ): KotlinRowResult? {
        return firstRowOrNull(sql, params.asList())
    }

    @Throws(SQLException::class)
    fun firstRowOrNull(
        sql: String,
        params: List<Any>,
    ): KotlinRowResult? {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun firstRowOrNull(
        sql: String,
        params: Map<*, *>,
    ): KotlinRowResult? {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class, NoSuchElementException::class)
    fun lastRow(
        sql: String,
    ): KotlinRowResult {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class, NoSuchElementException::class)
    fun lastRow(
        sql: String,
        params: Array<Any>,
    ): KotlinRowResult {
        return lastRow(sql, params.asList())
    }

    @Throws(SQLException::class, NoSuchElementException::class)
    fun lastRow(
        sql: String,
        params: List<Any>,
    ): KotlinRowResult {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class, NoSuchElementException::class)
    fun lastRow(
        sql: String,
        params: Map<*, *>,
    ): KotlinRowResult {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun lastRowOrNull(
        sql: String,
    ): KotlinRowResult? {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun lastRowOrNull(
        sql: String,
        params: Array<Any>,
    ): KotlinRowResult? {
        return lastRowOrNull(sql, params.asList())
    }

    @Throws(SQLException::class)
    fun lastRowOrNull(
        sql: String,
        params: List<Any>,
    ): KotlinRowResult? {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun lastRowOrNull(
        sql: String,
        params: Map<*, *>,
    ): KotlinRowResult? {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun execute(
        sql: String,
    ): Boolean {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun execute(
        sql: String,
        params: Array<Any>,
    ): Boolean {
        return execute(sql, params.asList())
    }

    @Throws(SQLException::class)
    fun execute(
        sql: String,
        params: List<Any>,
    ): Boolean {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun execute(
        sql: String,
        params: Map<*, *>,
    ): Boolean {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun execute(
        sql: String,
        processResults: (isResultSet: Boolean, rowResult: List<KotlinRowResult>, updateCount: Int) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun execute(
        sql: String,
        params: Array<Any>,
        processResults: (isResultSet: Boolean, rowResult: List<KotlinRowResult>, updateCount: Int) -> Unit,
    ) {
        execute(sql, params.asList(), processResults)
    }

    @Throws(SQLException::class)
    fun execute(
        sql: String,
        params: List<Any>,
        processResults: (isResultSet: Boolean, rowResult: List<KotlinRowResult>, updateCount: Int) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun execute(
        sql: String,
        params: Map<*, *>,
        processResults: (isResultSet: Boolean, rowResult: List<KotlinRowResult>, updateCount: Int) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun executeInsert(
        sql: String,
    ): List<List<Object>> {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun executeInsert(
        sql: String,
        params: Array<Any>,
    ): List<List<Object>> {
        return executeInsert(sql, params.asList())
    }

    @Throws(SQLException::class)
    fun executeInsert(
        sql: String,
        params: List<Any>,
    ): List<List<Object>> {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun executeInsert(
        sql: String,
        params: Map<*, *>,
    ): List<List<Object>> {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun executeUpdate(
        sql: String,
    ): Int {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun executeUpdate(
        sql: String,
        params: Array<Any>,
    ): Int {
        return executeUpdate(sql, params.asList())
    }

    @Throws(SQLException::class)
    fun executeUpdate(
        sql: String,
        params: List<Any>,
    ): Int {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun executeUpdate(
        sql: String,
        params: Map<*, *>,
    ): Int {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun call(
        sql: String,
    ): Int {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun call(
        sql: String,
        params: Array<Any>,
    ): Boolean {
        return call(sql, params.asList())
    }

    @Throws(SQLException::class)
    fun call(
        sql: String,
        params: List<Any>,
    ): Boolean {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun call(
        sql: String,
        params: Map<*, *>,
    ): Boolean {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun call(
        sql: String,
        body: (todo: Any) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun call(
        sql: String,
        params: Array<Any>,
        body: (todo: Any) -> Unit,
    ) {
        call(sql, params.asList(), body)
    }

    @Throws(SQLException::class)
    fun call(
        sql: String,
        params: List<Any>,
        body: (todo: Any) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun call(
        sql: String,
        params: Map<*, *>,
        body: (todo: Any) -> Unit,
    ) {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun callWithAllRows(
        sql: String,
        body: (todo: Any) -> Unit,
    ): List<List<KotlinRowResult>> {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun callWithAllRows(
        sql: String,
        params: Array<Any>,
        body: (todo: Any) -> Unit,
    ): List<List<KotlinRowResult>> {
        return callWithAllRows(sql, params.asList(), body)
    }

    @Throws(SQLException::class)
    fun callWithAllRows(
        sql: String,
        params: List<Any>,
        body: (todo: Any) -> Unit,
    ): List<List<KotlinRowResult>> {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun callWithAllRows(
        sql: String,
        params: Map<*, *>,
        body: (todo: Any) -> Unit,
    ): List<List<KotlinRowResult>> {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun callWithRows(
        sql: String,
        body: (todo: Any) -> Unit,
    ): List<KotlinRowResult> {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun callWithRows(
        sql: String,
        params: Array<Any>,
        body: (todo: Any) -> Unit,
    ): List<KotlinRowResult> {
        return callWithRows(sql, params.asList(), body)
    }

    @Throws(SQLException::class)
    fun callWithRows(
        sql: String,
        params: List<Any>,
        body: (todo: Any) -> Unit,
    ): List<KotlinRowResult> {
        TODO("Not yet implemented")
    }

    @Throws(SQLException::class)
    fun callWithRows(
        sql: String,
        params: Map<*, *>,
        body: (todo: Any) -> Unit,
    ): List<KotlinRowResult> {
        TODO("Not yet implemented")
    }

    // TODO: executeInsert|Update with keyColumnNames

    // TODO: with*

    // TODO: other?
}
