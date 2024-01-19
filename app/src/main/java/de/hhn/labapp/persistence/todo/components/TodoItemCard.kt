package de.hhn.labapp.persistence.todo.components

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.hhn.labapp.persistence.todo.model.entities.TodoItem
/**
 * Composable function representing a card displaying a TodoItem.
 *
 * @param item The TodoItem to be displayed.
 * @param onItemChecked Callback for handling checkbox state changes.
 * @param onClick Callback for handling clicks on the TodoItem card.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItemCard(
    item: TodoItem,
    onItemChecked: (Boolean, TodoItem) -> Unit,
    onClick: (TodoItem) -> Unit,
) {

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
        onClick = { onClick(item) }
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Checkbox for marking the TodoItem as completed
            Checkbox(
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.primary,
                ),
                checked = item.completed,
                onCheckedChange = { onItemChecked(it, item) },
            )
            // Text displaying the TodoItem's text
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = item.text,
            )
        }
    }
}