package de.hhn.labapp.persistence.todo.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import de.hhn.labapp.persistence.todo.viewmodel.TodoListViewModel

/**
 * Composable function representing the main screen of the Todo List app.
 */
@Composable
fun TodoList() {
    // Initialize the ViewModel
    val viewModel = TodoListViewModel()

    // Scaffold is a basic material design layout structure
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(

                onClick = viewModel::onAddItem,
            ) {
                Icon(Icons.Filled.Add, "Floating Action Button")
            }
        }
    ) { padding ->
        // Column layout for organizing components vertically
        Column(
            modifier = Modifier.padding(padding)
        ) {
            // Display the heading "Tasks"
            Heading(viewModel)
            // SearchBar for searching todo items
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp,)
                    .padding(bottom = 16.dp),
                onQueryChanged = viewModel::onQueryChanged,
            )
            // Display a list of todo items
            TodoItemsList(viewModel)
        }
        // Show the dialog for adding or editing todo items
        if (viewModel.showDialog) {
            TodoItemDialog(viewModel)
        }
    }
}
/**
 * Composable function for displaying the heading "Tasks".
 */
@Composable
private fun Heading(viewModel: TodoListViewModel) {
    val todoItemsState by viewModel.todoItems.collectAsState()

    // Load initial todoo items when the composable is launched
    LaunchedEffect(todoItemsState){
        viewModel.loadTodoListItems()
    }
    Text(
        modifier = Modifier.padding(
            horizontal = 4.dp,
            vertical = 16.dp,
        ),
        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
        fontWeight = FontWeight.Bold,
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append(
                    if (todoItemsState.isEmpty()) {
                        "No Todos found!"
                    } else {
                        "Tasks"
                    }
                )
            }
        }
    )
}
/**
 * Composable function for displaying the list of todo items.
 *
 * @param viewModel The ViewModel managing todo items.
 */
@Composable
private fun TodoItemsList(viewModel: TodoListViewModel) {
    // Collect the current state of todoItems using StateFlow
    val todoItemsState by viewModel.todoItems.collectAsState()
    LaunchedEffect(todoItemsState){
        viewModel.loadTodoListItems()
    }
    // Show a loading indicator if the todoItemsState is empty
    if (todoItemsState.isEmpty()){
        LinearProgressIndicator()
    }else {
        // LazyColumn is a composable for efficiently rendering lists
        LazyColumn {
            items(todoItemsState.toTypedArray().sortedBy { it.completed }
                // Iterate over todos items and display each in TodoItemCard
            ) { todoItem ->
                TodoItemCard(
                    item = todoItem,
                    onItemChecked = { isChecked, item ->

                        viewModel.onItemChecked(isChecked, item)
                    },
                    onClick = viewModel::onItemClicked,

                    )
            }
        }
    }
}
