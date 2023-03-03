package com.vipulkanade.myapplication

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSwitchScreen() {
        composeTestRule.setContent {
            SwitchScreen()
        }

        // Check that the two switches are displayed
        composeTestRule.onAllNodesWithContentDescription("Switch").assertCountEquals(2)

        // Click on the app switch and verify that its state has changed
        composeTestRule.onNodeWithContentDescription("App Switch").performClick()
        composeTestRule.onNodeWithText("App Switch Status: ON").assertIsDisplayed()

        // Click on the physical switch and verify that its state has changed
        composeTestRule.onNodeWithContentDescription("Physical").performClick()
        composeTestRule.onNodeWithText("Physical Switch Status: ON").assertIsDisplayed()
    }
}
