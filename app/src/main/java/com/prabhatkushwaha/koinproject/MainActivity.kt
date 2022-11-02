package com.prabhatkushwaha.koinproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prabhatkushwaha.koinproject.remote.dto.Post
import com.prabhatkushwaha.koinproject.remote.service.PostService
import com.prabhatkushwaha.koinproject.ui.theme.KoinProjectTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    val service by inject<PostService>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinProjectTheme {
                // A surface container using the 'background' color from the theme
                val posts = produceState<List<Post>>(initialValue = emptyList(), producer = {
                    value = service.getPost()
                })
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                  LazyColumn(){
                     items(posts.value){
                         Column(
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .padding(16.dp)
                         ) {
                             Text(text = it.title, fontSize = 20.sp)
                             Spacer(modifier = Modifier.height(4.dp))
                             Text(text = it.body, fontSize = 14.sp)
                             Divider()
                         }
                     }
                  }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KoinProjectTheme {
        Greeting("Android")
    }
}