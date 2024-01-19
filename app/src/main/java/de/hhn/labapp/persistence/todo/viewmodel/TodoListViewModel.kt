package de.hhn.labapp.persistence.todo.viewmodel

import android.service.autofill.UserData
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.hhn.labapp.persistence.todo.model.DatabaseProvider.withDatabase
import de.hhn.labapp.persistence.todo.model.entities.TodoItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing TodoList-related logic and data.
 */
class TodoListViewModel(

) : ViewModel() {

    // StateFlow for holding the list of TodoItems
    private val _todoItems = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoItems: StateFlow<List<TodoItem>> = _todoItems.asStateFlow()

    // MutableState for handling the text of the currently selected TodoItem
    var currentItemText by mutableStateOf("")

    // MutableState for handling the currently selected TodoItem
    var currentItem by mutableStateOf<TodoItem?>(null)

    // StateFlow for holding the search query
    private val _searchQuery = MutableStateFlow("")
    private val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    /**
     * Indicates whether the item details dialog should be displayed.
     */
    val showDialog: Boolean
        get() = currentItem != null
    /**
     * Checks if the 'Save' button should be enabled.
     */
    val isSaveEnabled: Boolean
        get() = currentItemText.isNotBlank()

    /**
     * Handles the event when a TodoItem is checked or unchecked.
     */
    fun onItemChecked(newValue: Boolean, item: TodoItem) {
        withDatabase {
            todoItemDao().updateTodoItem(item.copy(completed = newValue))
            Log.d("ClickedNow", newValue.toString())
            loadTodoListItems()
        }
    }
    /**
     * Handles the event when a TodoItem is clicked.
     */
    fun onItemClicked(item: TodoItem) {
        currentItem = item
    }
    /**
     * Deletes a TodoItem from the database and refreshes the list.
     */
    fun deleteItem(item: TodoItem): Boolean {
        withDatabase {
            item.id?.let { todoItemDao().deleteTodoItemById(it) }
        }
        viewModelScope.launch{
            delay(500)
            loadTodoListItems()
        }

        return true
    }
    /**
     * Handles the event when the 'Add' button is clicked, creating a new TodoItem.
     */
    fun onAddItem() {
        val item = TodoItem(text = "") // Todo was macht text = ""
        currentItem = item

    }
    /**
     * Handles the event when the 'Save' button is clicked, updating or inserting a TodoItem.
     */
    fun onSaveItem() {

        if (currentItemText.isBlank()) {
            return
        }

        val updatedItem =
            currentItem?.copy(text = currentItemText) ?: TodoItem(text = currentItemText)

        // Check if the item already exists in the database
        withDatabase {
            val itemExists = updatedItem.id?.let { todoItemDao().doesTodoItemExist(it) }

            if (itemExists == true) {
                withDatabase {
                    todoItemDao().updateTodoItem(updatedItem)
                }
            } else {
                withDatabase {
                    todoItemDao().insertTodoItem(updatedItem)
                }
            }
            // Refresh the todoItems state after the database operation
            viewModelScope.launch{
                delay(500)
                loadTodoListItems()
            }
        }
    }
    /**
     * Loads the list of TodoItems from the database, considering the current search query.
     */
    fun loadTodoListItems() {
        viewModelScope.launch {
            withDatabase {
                // Use a temporary variable to prevent concurrent modification
               val temp = if (searchQuery.value.isBlank()) {
                    todoItemDao().getAllTodoItems()
                } else {
                    todoItemDao().getFilteredTodoItems(searchQuery.value)
                }
                _todoItems.value = temp
            }
            Log.d("CheckDatabase", todoItems.value.toList().toString())
        }
    }
    /**
     * Handles the event when the search query is changed.
     */
    fun onQueryChanged(query: String) {
        viewModelScope.launch {
            _searchQuery.emit(query)
            loadTodoListItems()
        }
    }
}