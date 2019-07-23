package effective.com.lib.validator


import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.w3c.dom.Element


class ViewIdValidatorTest {

    private val fileName = "activity_main_start.xml"
    var element: Element = mockk()
    private val SCHEMA = "http://schemas.android.com/apk/res/android"
    private val ID = "id"

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Get file name without prefix`() {
        setReturnValueId("@+id/TitleImageButton")
        setReturnTagName("Button")
        ViewIdValidator(fileName, element).apply {
            assertEquals("main_start.xml", getFileNameWithoutPrefix(fileName))
        }
    }

    @Test
    fun `Get file name without extension`() {
        setReturnValueId("@+id/TitleImageButton")
        setReturnTagName("Button")
        ViewIdValidator(fileName, element).apply {
            assertEquals("activity_main_start", getFileNameWithoutExtension(fileName))
        }
    }

    @Test
    fun `check incorrect view id`() {
        setReturnValueId("@+id/mainStarttestTextView")
        setReturnTagName("TextView")
        ViewIdValidator(fileName, element).apply {
            assertFalse(validateId())
        }
    }

    @Test
    fun `check correct view id`() {
        setReturnValueId("@+id/mainStartTestTextView")
        setReturnTagName("TextView")
        ViewIdValidator(fileName, element).apply {
            assertTrue(validateId())
        }
    }

    @Test
    fun `check correct view id with empty content`() {
        setReturnValueId("@+id/mainStartTextView")
        setReturnTagName("TextView")
        ViewIdValidator(fileName, element).apply {
            assertTrue(validateId())
        }
    }

    @Test
    fun `Get start content index`() {
        setReturnValueId("@+id/TitleImageButton")
        setReturnTagName("Button")
        ViewIdValidator(fileName, element).apply {
            assertEquals(0, getStartContentIndex())
        }
    }

    @Test
    fun `Get end content index`() {
        setReturnValueId("@+id/fragmentMainTestLinearLayout")
        setReturnTagName("LinearLayout")
        ViewIdValidator(fileName, element).apply {
            assertEquals(16, getEndContentIndex())
        }
    }

    @Test
    fun `Get start content index with incorrect layout name`() {
        setReturnValueId("@+id/fragmentMainTestLinearLayout")
        setReturnTagName("LinearLayout")
        ViewIdValidator(fileName, element).apply {
            assertEquals(12, getStartContentIndex())
        }
    }

    @Test
    fun `Get expected view id`() {
        setReturnValueId("@+id/fragmentMainTestLinearLayout")
        setReturnTagName("LinearLayout")
        ViewIdValidator(fileName, element).apply {
            assertEquals("@+id/mainStartTestLinearLayout", getExpectedResult())

        }
    }

    private fun setReturnValueId(value: String) {
        every { element.getAttributeNS(SCHEMA, ID) } returns value
    }

    private fun setReturnTagName(value: String) {
        every { element.tagName } returns value
    }
}
