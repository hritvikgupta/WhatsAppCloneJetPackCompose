package com.example.whatsappclonejetpackcompose

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.whats_app_clone_jetpackcompose.Data.Chat
import com.example.whats_app_clone_jetpackcompose.Data.DummyData
import com.example.whats_app_clone_jetpackcompose.Data.Message

//class DetailsActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            WhatsAppCloneJetpackComposeTheme {
//                actionBar?.setDisplayHomeAsUpEnabled(true)
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    val chatID = intent.getIntExtra("extra_chatid", 0)
////                    DetailsView(chatID)
//                }
//            }
//        }
//    }
//}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsView(chatID:Int, onHome:()-> Unit) {
    val chat = DummyData.listChat.filter { it.id== chatID }[0]
    Scaffold(backgroundColor = Color(0xFFEDEDED)
    , topBar = {MessageTopBar(chat = chat,onHome)},
    bottomBar =  {MessageBox()},
    content = {MessageList()})
}

@Composable
fun MessageTopBar(chat: Chat,onHome:()-> Unit) {
//    val navController= rememberNavController()
    TopAppBar(
        title = {
            Row{
                Image(painter = rememberImagePainter(data = chat.imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape))
                Column(Modifier.padding(start = 16.dp)) {
                    Text(text = chat.name, fontSize = 17.sp)
                    Text(text = chat.time, fontSize = 17.sp, fontWeight = FontWeight.Light)
                }

            }

        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Call,
                    contentDescription = null,
                tint= Color.White)
                
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ThumbUp,
                    contentDescription = null,
                    tint= Color.White)

            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint= Color.White)

            }

        },
        navigationIcon = {
            Row(
              verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 4.dp, end = 0.dp)
                    .fillMaxWidth()
            ){
                IconButton(onClick =onHome) {
                    Toast(LocalContext.current)
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }

            }
        }
    )
    
}

@Composable
fun MessageList() {
    val listMessage = DummyData.listMessage
    LazyColumn{
        items(listMessage.size){index->
            Spacer(modifier = Modifier.height(8.dp))
            if(listMessage[index].isPeer){
                    peerBubble(listMessage[index])
            }else{
                   userBubble(listMessage[index])
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun userBubble(message:Message) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(80.dp, end = 10.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(color = Color(0xFFD0FFC4))){
        Row(Modifier.padding(all = 10.dp)) {
            Column(modifier = Modifier.weight(3.0f, true)) {
                Text(text = message.message,
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)
                
            }
            
        }
    }

}
@Composable
fun peerBubble(message:Message) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp, end = 80.dp)
        .background(Color.White)
        .clip(RoundedCornerShape(8.dp))){
        Row(Modifier.padding(10.dp)) {
            Column(Modifier.weight(3.0f, true)) {
                Text(text =message.message,
                    fontSize = 16.sp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis)

                
            }
            
        }
    }
}
@Composable
fun MessageBox() {
    var textState =  remember {
        mutableStateOf(TextFieldValue()) }
    Box(modifier = Modifier
        .background(Color.White)
        .clip(RoundedCornerShape(8.dp))
        ){
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            BasicTextField(value = textState.value,
                onValueChange = {textState.value = it},
            modifier= Modifier.weight(1f, true))

        Spacer(modifier = Modifier.size(12.dp))
        FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null
            )
        }
        }
    }
}