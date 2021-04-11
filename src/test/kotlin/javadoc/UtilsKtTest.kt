package javadoc

import org.junit.jupiter.api.Test
import strikt.api.*
import strikt.assertions.*

internal class UtilsKtTest {
    @Test
    fun `should escape a single double quote`() {
        expectThat(""" " """.withEscapedDoubleQuotes())
            .isEqualTo(""" \" """)
    }

    @Test
    fun `should not escape twice`() {
        expectThat(""" \" """.withEscapedDoubleQuotes())
            .isEqualTo(""" \" """)
    }

    @Test
    fun `should escape a double quote that is close to left brace`() {
        expectThat(""" {" """.withEscapedDoubleQuotes())
            .isEqualTo(""" {\" """)
    }

    @Test
    fun `should escape two double quotes separately`() {
        expectThat(""" "" """.withEscapedDoubleQuotes())
            .isEqualTo(""" \"\" """)
    }
}