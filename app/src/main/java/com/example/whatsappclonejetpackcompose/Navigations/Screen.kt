package com.example.whats_app_clone_jetpackcompose.Navigations

sealed class Screen(val route:String){
    object Home : Screen("home")
    object details : Screen("details")
    object login:Screen("login")
    object registration:Screen("registration")
    fun withArgs(vararg args:Int):String{
        return buildString {
            append(route)
            for (arg in args) {
                append("/$arg")
            }
        }
    }
}
