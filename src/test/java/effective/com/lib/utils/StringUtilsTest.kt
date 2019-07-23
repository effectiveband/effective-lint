package effective.com.lib.utils

import org.junit.Assert
import org.junit.Test

class StringUtilsTest {

    @Test
    fun `Get string before character`() {
        val sourceString = "test.com"
        Assert.assertEquals("test", sourceString.removeAfterCharacter('.'))
    }

    @Test
    fun `Get string after character`() {
        val sourceString = "test.com"
        Assert.assertEquals("com", sourceString.removeBeforeCharacter('.'))
    }

    @Test
    fun `Convert string to CamelCase`() {
        val sourceString = "example_test.com"
        Assert.assertEquals("exampleTestCom", sourceString.toCamelCase(false, '.', '_'))
    }

    @Test
    fun `Convert string to CamelCase with capitalize first letter`() {
        val sourceString = "example_test.com"
        Assert.assertEquals("ExampleTestCom", sourceString.toCamelCase(true, '.', '_'))
    }
}