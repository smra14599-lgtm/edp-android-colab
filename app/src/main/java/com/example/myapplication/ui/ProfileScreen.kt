package com.example.myapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProfileScreen(postsVm: PostsViewModel, themeVm: ThemeViewModel) {

    val posts by postsVm.posts.collectAsStateWithLifecycle()
    val darkTheme by themeVm.isDarkTheme.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(Icons.Default.Person, null, Modifier.size(96.dp))
        Spacer(Modifier.height(8.dp))
        Text("Sean Mikel Ra", style = MaterialTheme.typography.headlineMedium)
        Text("@smra14599", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.secondary)
        Spacer(Modifier.height(8.dp))
        Text("BSIT 3-2", style = MaterialTheme.typography.labelSmall)
        Spacer(Modifier.height(16.dp))
        Text("${posts.size} posts", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(24.dp))
        HorizontalDivider()
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Dark theme", modifier = Modifier.weight(1f))
            Switch(
                checked = darkTheme,
                onCheckedChange = { themeVm.onThemeChanged(it) },
            )
        }
    }
}
