package de.hhn.labapp.persistence.todo.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import de.hhn.labapp.persistence.todo.viewmodel.TodoListViewModel

/**
 * Composable function representing a dialog for adding or editing a TodoItem.
 *
 * @param viewModel The ViewModel managing TodoItems.
 */
@Composable
fun TodoItemDialog(viewModel: TodoListViewModel) {
    // Initialize the text field with the current TodoItem's text or an empty string
    viewModel.currentItemText = viewModel.currentItem?.text ?: ""

    fun onDismissRequest() {
        if (viewModel.currentItemText.isBlank()) {
            viewModel.deleteItem(viewModel.currentItem!!)
        }
        viewModel.currentItem = null
    }
    // Dialog composable for TodoItem editing or creation
    Dialog(
        onDismissRequest = ::onDismissRequest,
    ) {
        // Card composable with a border
        Card(
            modifier = Modifier.padding(4.dp),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        ) {
            // Column layout for organizing components vertically
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                // Text field for entering or editing TodoItem text
                TodoItemTextField(viewModel)
                // Row layout for buttons (Delete, Cancel, Save)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    // Delete button to delete the current TodoItem
                    TextButton(
                        onClick = {
                            viewModel.currentItem?.let { viewModel.deleteItem(it) }
                            onDismissRequest()
                        }
                    ) {
                        Text("Delete")
                    }
                    // Spacer to fill the available space between buttons
                    Spacer(modifier = Modifier.weight(1f))
                    // Cancel button to dismiss the dialog without saving changes
                    TextButton(onClick = ::onDismissRequest) {
                        Text("Cancel")
                    }

                    // Save button to save changes to the TodoItem
                    TextButton(
                        onClick = {
                            viewModel.onSaveItem()
                            onDismissRequest()
                        },
                        enabled = viewModel.isSaveEnabled,
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}
/**
 * Composable function representing an outlined text field for entering or editing TodoItem text.
 *
 * @param viewModel The ViewModel managing TodoItems.
 */
@Composable
private fun TodoItemTextField(viewModel: TodoListViewModel) {
    // OutlinedTextField composable for entering or editing TodoItem text
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        value = viewModel.currentItemText,
        onValueChange = { viewModel.currentItemText = it },
        label = { Text("Task description") },
    )
}