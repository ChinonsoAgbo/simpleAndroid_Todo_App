package de.hhn.labapp.persistence.todo.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.setValue

/**
 * Composable function for displaying a search bar with an outlined text field and optional clear button.
 *
 * @param modifier The modifier for the [OutlinedTextField].
 * @param onQueryChanged A callback function triggered when the search query changes.
 * @param query The current search query.
 * @param placeholder The placeholder text for the search field.
 */
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onQueryChanged: (String) -> Unit,
    query: String = "",
    placeholder: String = "Search",
) {
    // Remember the current search query using mutable state
    var searchQuery by remember { mutableStateOf(query) }

    // Compose an OutlinedTextField for entering the search query
    OutlinedTextField(
        modifier = modifier,
        singleLine = true,
        value = searchQuery,
        onValueChange = {
            // Update the search query and trigger the callback
            searchQuery = it
            onQueryChanged(it)
        },
        label = {
            Text(placeholder) // Display the placeholder text as a label
        },
        trailingIcon = {
            // Show a clear button if the search query is not empty
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        // Clear the search query and trigger the callback
                        searchQuery = ""
                        onQueryChanged("")
                    }
                ) {
                    // Display the clear icon with a tooltip
                    Icon(Icons.Filled.Clear, "Clear Search")
                }
            }
        }
    )
}