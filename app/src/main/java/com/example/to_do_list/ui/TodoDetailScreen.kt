package com.example.to_do_list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.to_do_list.viewmodel.TodoViewModel
import java.text.DateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(viewModel: TodoViewModel, todoId: Int, onBack: () -> Unit) {
    val todos by viewModel.todos.collectAsState()
    val todo = todos.find { it.id == todoId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da Tarefa") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (todo != null) {
            Column(
                Modifier.padding(padding).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(todo.title, style = MaterialTheme.typography.headlineMedium)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = todo.done,
                        onCheckedChange = { viewModel.toggleDone(todo.copy(done = it)) }
                    )
                    Text(if (todo.done) "Completo" else "Pendente")
                }

                Text(
                    "Criado: " + DateFormat.getDateTimeInstance().format(Date(todo.createdAt)),
                    style = MaterialTheme.typography.bodyMedium
                )

                Button(onClick = {
                    viewModel.deleteTodo(todo)
                    onBack()
                }) {
                    Text("Deletar Tarefa")
                }
            }
        }
    }
}