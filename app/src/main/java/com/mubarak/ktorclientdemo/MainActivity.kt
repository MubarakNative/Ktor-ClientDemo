package com.mubarak.ktorclientdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mubarak.ktorclientdemo.ui.MainViewModel
import com.mubarak.ktorclientdemo.ui.theme.KtorClientDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtorClientDemoTheme {
                Scaffold {
                    App(modifier = Modifier.padding(it))
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier,viewModel: MainViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(modifier = modifier) {
        items(uiState.value.posts) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(text = it.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = it.body, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}