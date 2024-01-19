package de.hhn.labapp.persistence.todo.model

import androidx.room.Database
import androidx.room.RoomDatabase
import de.hhn.labapp.persistence.todo.model.dao.TodoItemDao
import de.hhn.labapp.persistence.todo.model.entities.TodoItem

/**
 * Room database class that defines the entities and their relationships for the application.
 *
 * @property entities Array of entity classes included in the database.
 * @property version The version number of the database schema.
 */
@Database(entities = [TodoItem::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    /**
     * Abstract method to obtain the Data Access Object (DAO) for TodoItem entities.
     *
     * @return An instance of the TodoItemDao interface for database operations.
     */
    abstract fun todoItemDao(): TodoItemDao
}