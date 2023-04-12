package com.example.whats_app_clone_jetpackcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.whats_app_clone_jetpackcompose.Navigations.Screen
import com.example.whats_app_clone_jetpackcompose.ui.view.ChatViewModel
import com.example.whatsappclonejetpackcompose.DetailsView
import com.example.whatsappclonejetpackcompose.HomeActivity
import androidx.hilt.navigation.compose.hiltViewModel

//import com.example.whats_app_clone_jetpackcompose.ui.view.DetailStartActivity

//import com.example.whats_app_clone_jetpackcompose.ui.view.navigateView
@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController as NavHostController, startDestination = Screen.Home.route) {

        // First route : Home
        composable("home") {

            val vm: ChatViewModel=hiltViewModel()
            HomeActivity().HomeView(navController = navController, vm)

        }

        composable(Screen.details.route +"/{id}",
        arguments= listOf(
            navArgument("id"){
                type= NavType.IntType
            }
        )) {entry->
            entry.arguments?.getInt("id")
                ?.let { DetailsView(chatID = it, onHome = {navController.popBackStack()}) }
        }

    }
}

