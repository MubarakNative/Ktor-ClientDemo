package com.mubarak.ktorclientdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.ktorclientdemo.data.sources.remote.PostsApi
import com.mubarak.ktorclientdemo.data.sources.remote.dto.Posts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PostsUiState(
    val posts:List<Posts> = emptyList()
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val postsApi: PostsApi
):ViewModel() {

    private val _uiState: MutableStateFlow<PostsUiState> = MutableStateFlow(PostsUiState())
    val uiState: StateFlow<PostsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    posts = postsApi.getPosts()
                )
            }
        }
    }

}