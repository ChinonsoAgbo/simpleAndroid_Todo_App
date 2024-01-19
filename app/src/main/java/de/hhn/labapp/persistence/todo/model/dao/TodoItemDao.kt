package de.hhn.labapp.persistence.todo.model.dao

import androidx.room.*
import de.hhn.labapp.persistence.todo.model.entities.TodoItem

/**
 * Data Access Object (DAO) interface for TodoItem entities.
 */
@Dao
interface TodoItemDao {

    /**
     * Insert a new TodoItem into the database.
     *
     * @param todoItem The TodoItem to be inserted.
     */
    @Insert
    fun insertTodoItem(todoItem: TodoItem)

    /**
     * Get all TodoItems from the database.
     *
     * @return A list of all TodoItems in the database.
     */
    @Query("SELECT * FROM TodoItem")
    fun getAllTodoItems(): List<TodoItem>

    /**
     * Get a TodoItem by its ID from the database.
     *
     * @param itemId The ID of the TodoItem to be retrieved.
     * @return The TodoItem with the specified ID, or null if not found.
     */
    @Query("SELECT * FROM TodoItem WHERE id = :itemId")
    fun getTodoItemById(itemId: Int): TodoItem?

    @Query("SELECT COUNT(*) FROM TodoItem WHERE id = :itemId")
    fun doesTodoItemExist(itemId: Int): Boolean
    /**
     * Update an existing TodoItem in the database.
     *
     * @param todoItem The updated TodoItem.
     */
    @Update
    fun updateTodoItem(todoItem: TodoItem)

    /**
     * Delete a TodoItem from the database.
     *
     * @param todoItem The TodoItem to be deleted.
     */
    @Delete
     fun deleteTodoItem(todoItem: TodoItem)

    /**
     * Delete a TodoItem by its ID from the database.
     *
     * @param itemId The ID of the TodoItem to be deleted.
     */
    @Query("DELETE FROM TodoItem WHERE id = :itemId")
    fun deleteTodoItemById(itemId: Int)

    /**
     * Get filtered TodoItems from the database based on the search query.
     *
     * @param searchQuery The query to filter TodoItems.
     * @return A list of filtered TodoItems in the database.
     */
    @Query("SELECT * FROM TodoItem WHERE text LIKE '%' || :searchQuery || '%'")
    fun getFilteredTodoItems(searchQuery: String): List<TodoItem>
}