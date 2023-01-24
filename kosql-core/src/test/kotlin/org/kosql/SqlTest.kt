package org.kosql

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Tests for [Sql].
 *
 * @author Marcel Overdijk
 */
class SqlTest {

    @Test
    fun test() {
        Sql.withInstance("jdbc:") {
            it.execute("insert into")
        }

        val expected = 42
        assertEquals(expected, 42)
    }
}
