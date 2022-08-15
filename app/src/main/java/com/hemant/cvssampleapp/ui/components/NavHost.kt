package com.hemant.cvssampleapp.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hemant.cvssampleapp.HtmlParserUtil
import com.hemant.cvssampleapp.data.Item
import com.hemant.cvssampleapp.ui.AppScreenState
import com.hemant.cvssampleapp.ui.screens.DetailScreen
import com.hemant.cvssampleapp.ui.screens.HomeScreen
import com.hemant.cvssampleapp.ui.states.ProgressBarState
import com.hemant.cvssampleapp.ui.states.SearchBarState
import com.hemant.cvssampleapp.viewmodel.FeedSearchViewModel

@Composable
fun NavHostContent(
    navController: NavHostController,
    searchBarState: SearchBarState,
    progressBarState: ProgressBarState,
    currentFeedItemState: Int,
    feedSearchViewModel: FeedSearchViewModel,
    feeds: List<Item>
) {
    NavHost(
        navController = navController,
        startDestination = AppScreenState.Home.route
    ) {
        composable(
            AppScreenState.Home.route
        ) {
            HomeScreen(
                searchBarState = searchBarState,
                onSearchTextFieldClick = {
                    feedSearchViewModel.fetchPreviousSearchList()
                },
                onSearchIconClick = { tag ->
                    if (searchBarState != SearchBarState.Empty) {
                        feedSearchViewModel.fetchFeedsData(tag)
                    }
                },
                progressBarState = progressBarState,
                data = feeds,
                onGridItemClick = { index ->
                    feedSearchViewModel.updateCurrentFeedItem(index)
                    navController.navigate(AppScreenState.Detail.route)
                }
            )
        }
        composable(
            AppScreenState.Detail.route
        ) {
            val currentFeedItem = feedSearchViewModel.getFeedItems()[currentFeedItemState]
            DetailScreen(
                currentFeedItem,
                HtmlParserUtil.parseDescriptionData(currentFeedItem.description)[0],
                HtmlParserUtil.parseDescriptionData(currentFeedItem.description)[1],
                HtmlParserUtil.parseDescriptionData(currentFeedItem.description)[2],
            )
        }
    }
}