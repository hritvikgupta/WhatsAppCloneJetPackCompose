package com.example.whatsappclonejetpackcompose.ui.view

import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.whats_app_clone_jetpackcompose.Navigation
import com.example.whatsappclonejetpackcompose.HomeActivity
import com.example.whatsappclonejetpackcompose.ui.view.ui.theme.WhatsAppCloneJetPackComposeTheme
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import com.google.firebase.auth.FirebaseAuth
@AndroidEntryPoint
class loginActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsAppCloneJetPackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Navigation(navController)
//                    var bool by remember {
//                        mutableStateOf(false)
//                    }
//                    var register by remember{ mutableStateOf(false)}
//                    var isRegistered by remember {
//                        mutableStateOf(false)
//                    }

//
//                    if(bool){
//                        startActivity(Intent(this@loginActivity, HomeActivity::class.java))
//                    }
//                    if (register){
//                        registerView(){
//                            isRegistered = it
//                        }
//                    }
                }
            }
        }
    }
}
@Composable
fun loginViewComposable(navController: NavController) {
    val mContext = LocalContext.current
    Box(
        Modifier
            .padding(8.dp).
    fillMaxSize(),
    contentAlignment = Alignment.Center) {
        val email = remember {
            mutableStateOf(TextFieldValue())
        }
        val password= remember{ mutableStateOf(TextFieldValue())}
        var passwordVisible=remember{ mutableStateOf(false)}
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement =  Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(value = email.value,
                onValueChange = { email.value = it },
                placeholder = {Text("Enter Registered Email Address")},
                label = {Text("Email")},
                modifier= Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black,
                    fontSize = TextUnit.Unspecified),
                maxLines = 1



            )
            TextField(value = password.value,
                onValueChange = { password.value = it },
                label = {Text("Password")},
                placeholder = {Text("Enter Password")},
                modifier= Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black,
                    fontSize = TextUnit.Unspecified),
                maxLines = 1,
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible.value)
                        com.example.whatsappclonejetpackcompose.R.drawable.visibility
                    else com.example.whatsappclonejetpackcompose.R.drawable.visibility


                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible.value) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible.value= !passwordVisible.value}){
                        Icon(painter = painterResource(id = image), contentDescription =description )

                    }
                }


            )
            Spacer(Modifier.height(8.dp))
            Button(onClick = {
                if (password.value.text.isEmpty() or email.value.text.isEmpty()){
                    Toast.makeText(mContext, "Please Enter Valid Credentials", Toast.LENGTH_SHORT).show()


                }
                else{
                    checkLogin(email.value.text, password.value.text){
                        if(it){
                            navController.navigate("home")

                        }
                        else{
                            Toast.makeText(mContext, "Please Enter Valid Credentials", Toast.LENGTH_SHORT).show()

                        }
                    }
                }

                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                Row(Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                    Text(text = "Login")
                    Spacer(modifier = Modifier.padding(10.dp))
                    Icon(imageVector = Icons.Default.Send,
                        contentDescription = null)
                }


            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = {
                    navController.navigate("registration")
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                Row(Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = "Registration")
                    Spacer(modifier = Modifier.padding(10.dp))
                    Icon(imageVector = Icons.Default.Send,
                        contentDescription = null)
                }


            }

        }


    }

}

fun checkLogin(email:String, password:String, validLogin:(Boolean)->Unit){
    val mAuth = FirebaseAuth.getInstance()
    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{task->
        if(task.isSuccessful){
            validLogin(true)
        }else{
            validLogin(false)
        }
    }
}