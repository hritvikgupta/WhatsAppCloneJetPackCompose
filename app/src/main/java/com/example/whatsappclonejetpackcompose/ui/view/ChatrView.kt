package com.example.whats_app_clone_jetpackcompose.ui.view

//import com.example.whats_app_clone_jetpackcompose.DetailsActivity
//import com.example.whats_app_clone_jetpackcompose.DetailsActivity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.whats_app_clone_jetpackcompose.Data.Chat
//import com.example.whats_app_clone_jetpackcompose.Data.DummyData
import com.example.whats_app_clone_jetpackcompose.Data.userDataRepository
import com.example.whats_app_clone_jetpackcompose.Navigations.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@Composable
fun ChatView(navController:NavController, vm: ChatViewModel) {

    val dummyChat by vm.users.collectAsState()
    LazyColumn {
        items(dummyChat.size){index->
            val mContext = LocalContext.current
            ChatItem(dummyChat[index], index,mContext ,navController)
        }
    }
}


//fun openChat(index:Int, mContext: Context){
//    val intent = Intent(mContext, DetailsActivity::class.java)
//    intent.putExtra("extra_chatid", index)
//    mContext.startActivity(intent)

//}
//private var currentIndex:Int=0
//data class showD(var name: Boolean = false)

//@Composable
//fun navigateView(navController: NavController, showDialog:Boolean) {
//    if (true){
//        observeState().showDialog= false
//        navController.navigate(Screen.Details.route)
//    }
//}
@OptIn(ExperimentalCoilApi::class)
@Composable
fun ChatItem(chat:Chat, index:Int, mContext:Context,
             navController: NavController) {
     var showDetails = remember {
        mutableStateOf(false)
    }
    Row(Modifier.padding(horizontal=16.dp, vertical = 8.dp)) {
      Image(painter = rememberImagePainter(data = chat.imageUrl),
          contentDescription = null,
      modifier = Modifier
          .clip(CircleShape)
          .size(64.dp),
      contentScale = ContentScale.Crop)

    Column(Modifier.padding(horizontal =  8.dp)) {

        Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
            Text(chat.name, fontWeight = FontWeight.SemiBold , fontSize = 17.sp,
            modifier = Modifier.clickable {
//                openChat(index, mContext )
                showDetails.value  = true
//                currentIndex = index
//                /*Toask*/
//                showD(name = true)
                navController.navigate(Screen.details.withArgs(index))
//                Toast.makeText(mContext, ""+showDetails.value , Toast.LENGTH_SHORT).show()
            }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(chat.time , fontWeight = FontWeight.Light, fontSize = 12.sp)

        }
        Text(
            chat.lastMessage?:"",
            maxLines = 1,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color(0xFFebebeb))
    }
    }
}

@HiltViewModel
class ChatViewModel @Inject constructor(userDataRepository: userDataRepository):ViewModel(){
    private val Users = MutableStateFlow(userDataRepository.listChat())
    val users:StateFlow<List<Chat>> = Users
}