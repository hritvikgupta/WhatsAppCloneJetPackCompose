package com.example.whats_app_clone_jetpackcompose

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
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
import androidx.navigation.NavController
import com.example.whatsappclonejetpackcompose.ui.view.loginActivity
import com.example.whatsappclonejetpackcompose.ui.view.loginViewComposable
import com.example.whatsappclonejetpackcompose.ui.view.registerView

//import com.example.whats_app_clone_jetpackcompose.ui.view.DetailStartActivity

//import com.example.whats_app_clone_jetpackcompose.ui.view.navigateView
@Composable
fun Navigation(navController: NavController) {


    NavHost(navController = navController as NavHostController, startDestination = Screen.login.route) {

        // First route : Home
        composable("login") {
            val mContext= LocalContext.current
//            loginViewComposable(){
//                if (it.equals("login")){
//                    navController.navigate("home")
//                }

            loginViewComposable(navController = navController)

        }
        composable("registration"){
            registerView(navController = navController)
        }
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
                ?.let {
                    val vm: ChatViewModel=hiltViewModel()
                    DetailsView(chatID = it, onHome = {navController.popBackStack()}, vm)
                }
        }

    }
}

