package com.hemant.cvssampleapp.viewmodel

import android.content.SharedPreferences
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class FeedSearchViewModelTest {

    private val mockSharedPref = mockk<SharedPreferences>(relaxed = true)
    private val spyFeedSearchViewModel = spyk(FeedSearchViewModel(mockSharedPref))

    @Test
    fun `test updateSearchItemList`() {
        // Given
        val testList = mutableListOf<String>()
        val testInputList = listOf<String>("a", "b", "c", "d", "e", "f", "g")
        var currentElementCount = 0

        // Add 5 elements and test if those are added to the testList
        for (index in 0..4) {
            spyFeedSearchViewModel.updateSearchItemList(testList, testInputList[index])
            assertEquals(testInputList[index], testList[index])
            currentElementCount++
        }
        assertEquals(5, testList.size)

        // Add remaining elements and verify the size
        while (currentElementCount < testInputList.size) {
            spyFeedSearchViewModel.updateSearchItemList(
                testList,
                testInputList[currentElementCount]
            )
            currentElementCount++
        }
        assertEquals(5, testList.size)

        // Verify testList to have latest 5 elements
        for (i in 0 until testList.size) {
            assertEquals(testInputList[testInputList.size - 5 + i], testList[i])
        }
    }
}