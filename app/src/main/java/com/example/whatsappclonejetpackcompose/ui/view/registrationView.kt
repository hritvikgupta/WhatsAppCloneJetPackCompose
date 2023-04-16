package com.example.whatsappclonejetpackcompose.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whatsappclonejetpackcompose.Data.User
import com.example.whatsappclonejetpackcompose.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Context

@Composable
fun registerView(navController: NavController) {

    val mContext = LocalContext.current
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp),
    contentAlignment = Alignment.Center) {
        val email = remember {
            mutableStateOf(TextFieldValue())
        }
        val password= remember{ mutableStateOf(TextFieldValue()) }
        var passwordVisible= remember{ mutableStateOf(false) }

        val repassword= remember{ mutableStateOf(TextFieldValue()) }
        var repasswordVisible= remember{ mutableStateOf(false) }
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement =  Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(value = email.value,
                onValueChange = { email.value = it },
                placeholder = { Text("Enter Registered Email Address") },
                label = { Text("Email") },
                modifier= Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black,
                    fontSize = TextUnit.Unspecified),
                maxLines = 1



            )
            TextField(value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                placeholder = { Text("Enter Password") },
                modifier= Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black,
                    fontSize = TextUnit.Unspecified),
                maxLines = 1,
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible.value)
                        R.drawable.visibility
                    else R.drawable.visibility


                    // Please provide localized description for accessibility services
                    val description = if (passwordVisible.value) "Hide password" else "Show password"

                    IconButton(onClick = {passwordVisible.value= !passwordVisible.value}){
                        Icon(painter = painterResource(id = image), contentDescription =description )

                    }
                }


            )
            TextField(value = repassword.value,
                onValueChange = { repassword.value = it },
                label = { Text("Confirm Password") },
                placeholder = { Text("Re-enter Password") },
                modifier= Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black,
                    fontSize = TextUnit.Unspecified),
                maxLines = 1,
                visualTransformation = if (repasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (repasswordVisible.value)
                        R.drawable.visibility
                    else R.drawable.visibility


                    // Please provide localized description for accessibility services
                    val description = if (repasswordVisible.value) "Hide password" else "Show password"

                    IconButton(onClick = {repasswordVisible.value= !repasswordVisible.value}){
                        Icon(painter = painterResource(id = image), contentDescription =description )

                    }
                }


            )
            Spacer(Modifier.height(8.dp))
            val mContext = LocalContext.current
            Button(onClick = {
                if (password.value.text.isEmpty() or email.value.text.isEmpty() or (password.value.text !=repassword.value.text)){
                    Toast.makeText(mContext, "Please Enter Valid Credentials", Toast.LENGTH_SHORT).show()

                }
                else{
                    registerUser(email = email.value.text,
                        password = password.value.text
                                ){
                        if(it){
                            navController.navigate("login")
                        }
                        else{
                            Toast.makeText(mContext, "Can't Process Login${email.value.text}/t${password.value.text}", Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                Row(
                    Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = "Register")
                    Spacer(modifier = Modifier.padding(10.dp))
                    Icon(imageVector = Icons.Default.Send,
                        contentDescription = null)
                }



            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = {navController.navigate("login")
            },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)) {
                Row(
                    Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = "Go To Login")
                    Spacer(modifier = Modifier.padding(10.dp))
                    Icon(imageVector = Icons.Default.Send,
                        contentDescription = null)
                }



            }




        }


    }

}

fun registerUser(email:String, password:String, registered:(Boolean)->Unit){
    val mAuth = FirebaseAuth.getInstance()
    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{task->
        if(task.isSuccessful){
            AddUserToDataBase(email,mAuth.currentUser?.uid!!)
            registered(true)
        }
        else{
            registered(false)
        }

    }

}
private fun AddUserToDataBase(email:String,uid:String)
{
    val mDbRef = FirebaseDatabase.getInstance().getReference()
    mDbRef.setValue(User(email,uid))

}