package de.hhn.labapp.persistence.todo.model

data class TodoItem(
    val id: Int? = null,
    var text: String,
    var completed: Boolean = false
)
