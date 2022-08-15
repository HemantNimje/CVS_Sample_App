package com.hemant.cvssampleapp.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemant.cvssampleapp.data.Item
import com.hemant.cvssampleapp.network.RetrofitClient
import com.hemant.cvssampleapp.ui.states.ProgressBarState
import com.hemant.cvssampleapp.ui.states.SearchBarState
import kotlinx.coroutines.launch

class FeedSearchViewModel(
    private val sharedPref: SharedPreferences
) : ViewModel() {
    private val feedItemMutableStateList = mutableStateListOf<Item>()

    private val progressBarState = mutableStateOf<ProgressBarState>(ProgressBarState.Hide)
    private val searchBarState = mutableStateOf<SearchBarState>(SearchBarState.Empty)
    private val previousSearchList = mutableStateListOf<String>()
    private val currentFeedItemState = mutableStateOf(0)

    fun getProgressBarState(): State<ProgressBarState> = progressBarState
    fun getSearchBarState() = searchBarState
    fun getFeedItems(): SnapshotStateList<Item> = feedItemMutableStateList
    fun getCurrentFeedItem(): State<Int> = currentFeedItemState

    /**
     * Fetches feeds from cloud using
     * @param tag - search string entered by user
     */
    fun fetchFeedsData(tag: String) {
        // Update search bar, progress bar state
        searchBarState.value = SearchBarState.InputComplete
        progressBarState.value = ProgressBarState.Show

        // Fetch data using Retrofit
        viewModelScope.launch {
            updateSearchItemList(previousSearchList, tag)
            writeIntoSharedPreference(sharedPref, previousSearchList.toList())

            val feeds = RetrofitClient.feedApi.getPhotos(tag = tag)
            Log.d(TAG, feeds.toString())

            // On successful fetch update Progress bar state, update list to show data on screen
            progressBarState.value = ProgressBarState.Hide
            feedItemMutableStateList.clear()
            feedItemMutableStateList.addAll(feeds.items)
        }
    }

    /**
     * Fetches recent search list from the shared preferences and updates local
     * MutableStateList<String> previousSearchList
     */
    fun fetchPreviousSearchList() {
        searchBarState.value = SearchBarState.InputInProgress(
            previousSearchList
        )

        viewModelScope.launch {
            val recentSearchString: List<String> = readFromSharedPreference(sharedPref)
            previousSearchList.clear()
            if (recentSearchString.isNotEmpty()) {
                recentSearchString.forEach {
                    updateSearchItemList(previousSearchList, it)
                }
            }
        }
    }

    /**
     * Update search item list to contain latest 5 elements
     * @param list - MutableList<String> containing latest 5 recent search strings
     * @param value - new search string to be added to the list
     */
    @VisibleForTesting
    fun updateSearchItemList(list: MutableList<String>, value: String) {
        if (list.size < RECENT_SEARCH_LIST_SIZE) {
            list.add(value)
        } else if (list.size == RECENT_SEARCH_LIST_SIZE) {
            list.removeFirst()
            list.add(value)
        }
    }

    /**
     * Read from Shared Preferences
     * @param sharedPref - SharedPreferences
     * @return List<String> - retrieve comma separated string from shared preferences and
     * returns List<String>
     */
    private fun readFromSharedPreference(sharedPref: SharedPreferences): List<String> {
        val sharedPrefString = sharedPref.getString(SHARED_PREF_KEY, SHARED_PREF_DEFAULT_VALUE)
        var resultList: List<String> = listOf()

        sharedPrefString?.let {
            if (sharedPrefString != "") {
                resultList = it.split(DELIMITER)
            }
        }
        return resultList
    }

    /**
     * Write into Shared Preferences
     * @param sharedPref - SharedPreferences
     * @param value - List<String> to write in sharedPreferences
     */
    private fun writeIntoSharedPreference(sharedPref: SharedPreferences, value: List<String>) {
        with(sharedPref.edit()) {
            putString(SHARED_PREF_KEY, value.joinToString(","))
            apply()
        }
    }

    /**
     * Update current feed item state
     * @param index of the item from grid view selected by user
     */
    fun updateCurrentFeedItem(index: Int) {
        currentFeedItemState.value = index
    }

    companion object {
        const val TAG = "FeedSearchViewModel"
        const val SHARED_PREF_KEY = "RecentSearchListInStringForm"
        const val SHARED_PREF_DEFAULT_VALUE = ""
        const val DELIMITER = ","
        const val RECENT_SEARCH_LIST_SIZE = 5
    }
}