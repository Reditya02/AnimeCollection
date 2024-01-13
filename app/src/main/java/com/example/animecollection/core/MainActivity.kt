package com.example.animecollection.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.animecollection.ui.NavGraphs
import com.example.animecollection.ui.theme.AnimeCollectionTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeCollectionTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}