package com.example.whats_app_clone_jetpackcompose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter
import com.example.whats_app_clone_jetpackcompose.Data.Call
import com.example.whats_app_clone_jetpackcompose.Data.Chat
import com.example.whats_app_clone_jetpackcompose.Data.DummyData
import com.example.whats_app_clone_jetpackcompose.Data.userDataRepository
import com.example.whatsappclonejetpackcompose.R
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@Composable
fun ContactView(vm:contactViewModel) {
    val dummyData by vm.Calls.collectAsState()
    LazyColumn{
        items(dummyData.size){index ->
        CallItem(dummyData[index])
        }
    }

}

@Composable
fun CallItem(call: Call) {
    Row(Modifier.padding(horizontal = 16.dp, vertical =8.dp)) {
        Box(modifier = Modifier.weight(1f)){
           Image(painter = rememberImagePainter(data = call.imageUrl),
               contentDescription =null,
           modifier = Modifier
               .padding()
               .clip(CircleShape)
               .size(64.dp),
           contentScale = ContentScale.Crop)
        }
        Column(
            Modifier
                .padding(horizontal = 8.dp)
                .weight(7f)
        ) {
            Text(text=call.name, maxLines = 1, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
            Row(Modifier.fillMaxWidth()){
                if(call.isMissCall){
                    Icon(painter = painterResource(id = R.drawable.ic_missed_call), contentDescription =null , tint = Color.Red)

                }
                else{
                    Icon(painter = painterResource(id = R.drawable.ic_missed_call), contentDescription =null, tint = Color.Green )

                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(call.date, fontSize = 15.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(14.dp))
            Divider(color = Color(0xFFebebeb))
            
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Call, contentDescription = null
            , tint = MaterialTheme.colors.primary)

            
        }

    }
    
}

@HiltViewModel
class contactViewModel @Inject constructor(userDataRepository: userDataRepository): ViewModel() {
    private val calls = MutableStateFlow(userDataRepository.listCall())
    val Calls:StateFlow<List<Call>> = calls
}