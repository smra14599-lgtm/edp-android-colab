package com.example.myapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.data.Post

@Composable
fun PostsScreen(vm: PostsViewModel) {

    val posts by vm.posts.collectAsStateWithLifecycle()
    var showEditor by remember { mutableStateOf(false) }
    var editing by remember { mutableStateOf<Post?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { editing = null; showEditor = true }) {
                Icon(Icons.Default.Add, null)
            }
        }
    ) { padding ->
        if (posts.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No posts yet...")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(posts, key = { it.id }) { post ->
                    PostCard(
                        post = post,
                        onEdit = { editing = post; showEditor = true },
                        onDelete = { vm.deletePost(post) },
                    )
                }
            }
        }
    }

    if (showEditor) {
        PostEditorDialog(
            initialText = editing?.content ?: "",
            onDismiss = { showEditor = false },
            onSave = { newText ->
                val current = editing
                if (current == null) vm.addPost(newText) else vm.editPost(current, newText)
                showEditor = false
            },
        )
    }
}

@Composable
fun PostCard(post: Post, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {
            Text(post.content, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextButton(onClick = onEdit) { Text("Edit") }
                TextButton(onClick = onDelete, colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)) {
                    Text("Delete")
                }
            }
        }
    }
}

@Composable
fun PostEditorDialog(
    initialText: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit,
) {
    var text by rememberSaveable { mutableStateOf(initialText) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (initialText.isEmpty()) "New post" else "Edit post") },
        text = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("What's on your mind?") },
                minLines = 3,
            )
        },
        confirmButton = {
            TextButton(onClick = { onSave(text.trim()) }, enabled = text.isNotBlank()) {
                Text("Save")
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}
