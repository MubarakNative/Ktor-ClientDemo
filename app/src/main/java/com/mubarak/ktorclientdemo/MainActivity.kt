package com.mubarak.ktorclientdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubarak.ktorclientdemo.data.sources.remote.PostsApi
import com.mubarak.ktorclientdemo.data.sources.remote.dto.Posts
import com.mubarak.ktorclientdemo.ui.theme.KtorClientDemoTheme

class MainActivity : ComponentActivity() {

    private val service = PostsApi.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtorClientDemoTheme {
                Scaffold {
                    App(service = service, modifier = Modifier.padding(it))
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier,service: PostsApi) {

    val posts = produceState<List<Posts>>(initialValue = emptyList()) {
        value = service.getPosts()
    }
    LazyColumn(modifier = modifier) {
        items(posts.value) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(text = it.title, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = it.body, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}