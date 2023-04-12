package com.example.whatsappclonejetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whats_app_clone_jetpackcompose.composable.Center
import com.example.whatsappclonejetpackcompose.ui.theme.WhatsAppCloneJetPackComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
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
                    SplashView()
                    compositionScope.launch {
                        delay(1000)
                        startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                    }
                }
            }
        }
    }

    @Composable
    fun SplashView() {
        Center(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(painter = painterResource(id = R.drawable.ic_whatsapp) ,
                contentDescription =null,
                modifier = Modifier.size(100.dp))
        }
    }
}

