package com.codelytical.photohunt.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.codelytical.photohunt.viewmodel.HuntViewModel

@Composable
fun HandleBackPressToHome(navController: NavHostController, huntViewModel: HuntViewModel) {
    BackHandler {
        huntViewModel.reset()
        navController.popBackStack(Screen.Home.route, inclusive = false)
    }
}