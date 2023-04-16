package com.example.whatsappclonejetpackcompose

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whats_app_clone_jetpackcompose.Navigation
import com.example.whats_app_clone_jetpackcompose.Navigations.Screen
import com.example.whats_app_clone_jetpackcompose.composable.AppTabs
import com.example.whats_app_clone_jetpackcompose.composable.AppTopBar
import com.example.whats_app_clone_jetpackcompose.ui.view.*
//import com.example.whats_app_clone_jetpackcompose.ui.view.DetailStartActivity
import com.example.whatsappclonejetpackcompose.ui.theme.WhatsAppCloneJetPackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

enum class HomeTab{
    CHATS, STATUS, CONTACTS
}

class HomeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val NavController  = rememberNavController()
//            NavHost(navController = NavController, startDestination = Screen.HomeScreen.route){
//                composable(Screen.HomeScreen.route){
//
//                }
//            }
            WhatsAppCloneJetPackComposeTheme {

                actionBar?.setDisplayHomeAsUpEnabled(true)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {


                }
            }
        }
    }

//    @Composable
//    fun Navigation() {
//
//        val navController = rememberNavController()
//
//        NavHost(navController = navController as NavHostController, startDestination = Screen.Home.route) {
//
//            // First route : Home
//            composable("home") {
//
//                // Lay down the Home Composable
//                // and pass the navController
//                HomeView(navController = navController)
//            }
//
//            // Another Route : Profile
//            composable(Screen.details.route) {
//                // Profile Screen
////                val intent = Intent(LocalContext.current, DetailsActivity::class.java)
////                intent.putExtra("extra_chatid", 0)
////                LocalContext.current.startActivity(intent)
//                DetailStartActivity(onHome = {navController.popBackStack()})
//            }
//
//        }
//    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun HomeView(navController:NavController, vm:ChatViewModel) {
        var showMenu by remember { mutableStateOf(false)}
        var tabSelected by remember { mutableStateOf(HomeTab.CHATS)}

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("WhatsApp") },
                    elevation = 0.dp,
                    actions = {
                        DropdownMenu(expanded = showMenu ,
                            onDismissRequest = { showMenu =false}) {
                            DropdownMenuItem(onClick = {}) {
                                Text("WhatsApp Web")

                            }
                            DropdownMenuItem(onClick = {}) {
                                Text("Settings")

                            }
                            DropdownMenuItem(onClick = {
                                navController.navigate("login")
                            }) {
                                Text("Logout")

                            }


                        }
                        IconButton(onClick = { }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null )
                        }
                        IconButton(onClick = {showMenu = showMenu!=true}) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null )

                        }
                    }
                )
            },
            floatingActionButton = {
                when(tabSelected) {
                    HomeTab.CHATS -> {
                        FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = Color(0xFF075E54)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_message),
                                contentDescription = null,
                                tint = Color.White
                            )

                        }
                    }
                    HomeTab.STATUS -> {

                        Column{
                            FloatingActionButton(onClick = { /*TODO*/ },
                                backgroundColor = Color.White,
                                modifier= Modifier.size(40.dp)) {
                                Icon(painter = painterResource(id = R.drawable.ic_create),
                                    contentDescription = null, tint = Color.DarkGray)


                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = Color(0xFF075E54)) {
                                Icon(painter = painterResource(id = R.drawable.ic_camera),
                                    contentDescription = null,
                                    tint = Color.White)

                            }
                        }
                    }
                    HomeTab.CONTACTS->{
                        FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = Color(0xFF075E54)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add_call),
                                contentDescription = null,
                                tint = Color.White
                            )

                        }
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        ) {
            Column(Modifier.fillMaxSize()) {
                HomeTopBar(
                    tabSelected,
                    onTabSelected = {tabSelected = it}
                )
                when(tabSelected){
                    HomeTab.CHATS ->ChatView(navController, vm)
                    HomeTab.STATUS -> StatusView()
                    HomeTab.CONTACTS -> {
                        val vm:contactViewModel= hiltViewModel()
                        ContactView(vm)
                    }
                }
            }

        }
    }
}
@Composable
fun HomeTopBar(
    tabSelected:HomeTab,
    onTabSelected:(HomeTab)->Unit
) {
    AppTopBar {topBarModifier->
        AppTabs(
            modifier = topBarModifier,
            titles = HomeTab.values().map{it.name},
            tabSelected = tabSelected,

            onTabSelected = {newTab->
                onTabSelected(HomeTab.values()[newTab.ordinal])

            }
        )
    }

}