package com.alepagani.habitsapp.onboarding.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alepagani.habitsapp.core.presentation.HabitButton
import com.alepagani.habitsapp.core.presentation.HabitTitle
import com.alepagani.habitsapp.onboarding.presentation.OnboardingPagerInformation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingPages(
    pages: List<OnboardingPagerInformation>,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {

    val pageState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.background(Color.White)) {
        HorizontalPager(count = pages.size, state = pageState) { index->

            val information = pages[index]
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                HabitTitle(title = information.title, modifier = Modifier.padding(horizontal = 16.dp))
                Image(
                    painter = painterResource(id = information.image),
                    contentDescription = "Image Onboarding",
                    modifier = Modifier.aspectRatio(1f),
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = information.subtitle.uppercase(),
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = androidx.compose.material3.MaterialTheme.colorScheme.tertiary
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(bottom = 64.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            if (pageState.currentPage == pages.lastIndex) {
                HabitButton(text = "Get Started", onClick = onFinish, modifier = Modifier.fillMaxWidth())
            } else {
                TextButton(onClick = onFinish) {
                    Text(text = "Skip", color = androidx.compose.material3.MaterialTheme.colorScheme.tertiary)
                }
                HorizontalPagerIndicator(
                    pagerState = pageState,
                    activeColor = androidx.compose.material3.MaterialTheme.colorScheme.tertiary,
                    inactiveColor = androidx.compose.material3.MaterialTheme.colorScheme.primary
                    )
                TextButton(onClick = {
                    coroutineScope.launch {
                        pageState.animateScrollToPage(pageState.currentPage + 1)
                    }
                }) {
                    Text(text = "Next", color = androidx.compose.material3.MaterialTheme.colorScheme.tertiary)
                }
            }
        }
    }
}