package com.example.whatsappclonejetpackcompose.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.whatsappclonejetpackcompose.HomeActivity
import com.example.whatsappclonejetpackcompose.ui.theme.WhatsAppCloneJetPackComposeTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class loginView:AppCompatActivity(){
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Navigation()
            WhatsAppCloneJetPackComposeTheme {
                actionBar?.setDisplayHomeAsUpEnabled(true)
                // A surface container using the 'background' color from the theme
                val  compositionScope = rememberCoroutineScope()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val bool = loginViewComposable()
                    compositionScope.launch {
                        delay(1000)
                        if(bool){
                            startActivity(Intent(this@loginView, HomeActivity::class.java))
                        }
                    }
                }
            }
        }
}
@Composable
fun loginViewComposable():Boolean {

    var goToHome:Boolean = false
    Box(
        Modifier
            .size(64.dp)
            .padding(8.dp)) {
        val input = remember {
            mutableStateOf(TextFieldValue())
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement =  Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
                    TextField(value = input.value,
            onValueChange = { input.value = it },
                    placeholder = {Text("Enter Registered Mobile Number")},
                    modifier= Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black,
                    fontSize = TextUnit.Companion.Unspecified),
                        maxLines = 1
                   

                    )
            Spacer(Modifier.height(8.dp))
            IconButton(onClick = {
                goToHome =true
            }) {
                Icon(imageVector = Icons.Default.Send,
                    contentDescription = null)

            }

        }

        
    }
    return goToHome
}

//@Composable
//fun goToHomeActivity(bool:Boolean){
//    val mContext = LocalContext.current
//    if(bool){
//        val intent = Intent(LocalContext.current, HomeActivity::class.java)
//            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
//
//    }

}

