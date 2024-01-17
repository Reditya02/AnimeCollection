package com.example.animecollection.ui.splashscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.animecollection.R
import com.example.animecollection.ui.destinations.LoginScreenDestination
import com.example.animecollection.ui.destinations.MainScreenDestination
import com.example.animecollection.ui.destinations.TrendingScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
    viewModel: SplashScreenViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        delay(1000)
        if (viewModel.getUid().isNotEmpty())
            navigator.navigate(MainScreenDestination)
        else
            navigator.navigate(LoginScreenDestination)
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.weight(0.3f))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.4f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.RawRes(R.raw.anim_loading)
            )

            Row(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(0.2f))
                LottieAnimation(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(0.6f),
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.weight(0.2f))
            }
        }
        Spacer(modifier = Modifier.weight(0.3f))
    }
}