package com.hemant.cvssampleapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.hemant.cvssampleapp.ui.components.NavHostContent
import com.hemant.cvssampleapp.ui.theme.CVSSampleAppTheme
import com.hemant.cvssampleapp.viewmodel.FeedSearchViewModel

class MainActivity : ComponentActivity() {

    private lateinit var feedSearchViewModel: FeedSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        feedSearchViewModel = FeedSearchViewModel(
            this.getPreferences(
                Context.MODE_PRIVATE
            )
        )

        setContent {
            CVSSampleAppTheme {
                // A surface container using the 'background' color from the theme
                AppScreenContent()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppScreenContent() {

        val navController = rememberNavController()

        val searchBarState = remember {
            feedSearchViewModel.getSearchBarState()
        }

        val progressBarState = remember {
            feedSearchViewModel.getProgressBarState()
        }

        val feeds = remember {
            feedSearchViewModel.getFeedItems()
        }

        val currentFeedItemState = remember {
            feedSearchViewModel.getCurrentFeedItem()
        }

        Column(
            modifier = Modifier
        ) {
            Scaffold(
            ) {
                Box(
                    modifier = Modifier
                        .padding(it)
                ) {
                    NavHostContent(
                        navController = navController,
                        searchBarState = searchBarState.value,
                        progressBarState = progressBarState.value,
                        currentFeedItemState = currentFeedItemState.value,
                        feedSearchViewModel = feedSearchViewModel,
                        feeds = feeds
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        CVSSampleAppTheme {
            AppScreenContent()
        }
    }
}