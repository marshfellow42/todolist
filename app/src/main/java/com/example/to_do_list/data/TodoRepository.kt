package com.example.to_do_list.data

import kotlinx.coroutines.flow.Flow

class TodoRepository(private val dao: TodoDao) {
    val allTodos: Flow<List<Todo>> = dao.observeAll()

    suspend fun addTodo(todo: Todo) = dao.insert(todo)
    suspend fun deleteTodo(todo: Todo) = dao.delete(todo)
    suspend fun updateTodo(todo: Todo) = dao.update(todo)
}