package com.example.whats_app_clone_jetpackcompose.ui.view

//import com.example.whats_app_clone_jetpackcompose.ui.theme.camera
//import com.example.whats_app_clone_jetpackcompose.ui.theme.note
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.whatsappclonejetpackcompose.ui.theme.Teal200
import com.example.whatsappclonejetpackcompose.R


@Composable
fun StatusView() {
//    createUserStatus()
//    Spacer(modifier = Modifier.height(10.dp))

    createFriendsStoryView()

}

@Composable
fun createFriendsStoryView(modifier: Modifier = Modifier) {
//    Box(modifier = Modifier
//        .fillMaxWidth()
//    ){
//        Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
//            Text(text = "I am user")
//        }
//
//    }
    val userStatusImage = "https://randomuser.me/api/portraits/men/12.jpg"

    Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {

        Box(modifier = Modifier.weight(2f)) {
            Image(
                painter = rememberImagePainter(data = userStatusImage),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(48.dp)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )
            Icon(
                imageVector = Icons.Default.AddCircle, contentDescription = null,
                tint = Color.Green,
                modifier = Modifier.offset(x = 32.dp, y = 32.dp)
            )


        }

        Column(
            Modifier
                .padding(horizontal = 8.dp)
                .weight(8f)) {
            Text(text = "Status Saya", maxLines = 1, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = "Some Status about something", fontSize = 15.sp, color = Color.Gray)

        }
    }
}
@Composable
fun createUserStatus(
    modifier: Modifier = Modifier
    ) {
    val userStatusImage = "https://randomuser.me/api/portraits/men/12.jpg"
    val seenColor = Teal200
    val unseenColor = Color.LightGray
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 5.dp, vertical = 16.dp)
        .background(color = Color.Transparent)) {
        Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Image(
                painter = rememberImagePainter(data = userStatusImage),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(64.dp)
                    .border(5.dp, seenColor, CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(Modifier.padding(horizontal = 8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(modifier = Modifier.weight(2f), horizontalArrangement = Arrangement.Start) {
                        Text("My Status", modifier = Modifier.weight(2f))
                    }
//                    Spacer(modifier = Modifier.weight(2f))
//                    Text(text = "time added")
                    Row(modifier.weight(1f), horizontalArrangement = Arrangement.End) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(18.dp)
                                .weight(1f),
                        )
//                    Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.ic_notes),
                            contentDescription = null,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(18.dp)
                                .weight(1f)
                        )
                    }

                }
                Text(
                    "Add to my Status",
                    fontSize = 15.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))


            }
        }

    }
}