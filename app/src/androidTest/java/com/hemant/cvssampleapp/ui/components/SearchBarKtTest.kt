package com.hemant.cvssampleapp.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.hemant.cvssampleapp.ui.theme.CVSSampleAppTheme
import org.junit.Rule
import org.junit.Test

class SearchBarKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRecentSearchItem() {
        var testRecentSearchItemBefore = "TestBefore"
        val testRecentSearchItemAfter = "TestAfter"

        composeTestRule.setContent {
            CVSSampleAppTheme {
                RecentSearchItem(
                    recentSearchItem = testRecentSearchItemBefore,
                    onClick = { testRecentSearchItemBefore = testRecentSearchItemAfter }
                )
            }
        }

        composeTestRule.onNodeWithText(testRecentSearchItemBefore).performClick()
        assert(testRecentSearchItemBefore == testRecentSearchItemAfter)
    }
}