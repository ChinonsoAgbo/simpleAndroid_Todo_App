package de.hhn.labapp.persistence.todo.model

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Singleton object responsible for providing access to the Room database.
 */
object DatabaseProvider {
    private lateinit var database:AppDatabase
    private set
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    /**
     * Initializes the Room database with the provided context.
     *
     * @param context The application context.
     */
    fun init(context: Context){
        database = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "todoApp_exercise3"
        ).build()

    }
    /**
     * Executes a database block within the defined coroutine scope.
     *
     * @param block The block of code to be executed on the database.
     * @return A [Job] representing the execution of the database block.
     */
    fun withDatabase(block: AppDatabase.()->Unit): Job {
        return coroutineScope.launch { database.block() }
    }

}