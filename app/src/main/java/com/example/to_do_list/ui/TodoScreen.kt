package com.example.to_do_list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.to_do_list.data.Todo
import com.example.to_do_list.viewmodel.TodoViewModel

@Composable
fun TodoScreen(viewModel: TodoViewModel, onTodoClick: (Todo) -> Unit, modifier: Modifier = Modifier) {
    val todos by viewModel.todos.collectAsState()
    var text by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row {
            OutlinedTextField(
                value = text,
                onValueChange = {text = it},
                placeholder = {Text("Coloque uma tarefa:")},
                modifier = modifier.weight(1f)
            )

            IconButton(
                modifier = modifier.height(50.dp),
                onClick = {
                    if (text.isNotBlank()) {
                        viewModel.addTodo(text)
                        text = ""
                    }
                }
            ) {
                Icon(Icons.Default.AddCircle, contentDescription = "Adicionar")
            }
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(todos, key = { it.id }) { todo ->
                TodoItem(
                    todo = todo,
                    onClick = { onTodoClick(todo) },
                    onDelete = viewModel::deleteTodo,
                    onToggle = viewModel::toggleDone
                )
            }
        }
    }
}