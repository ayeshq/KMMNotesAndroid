package com.kmm.notes.entity.cache

import com.kmm.notes.cache.AppDatabase
import com.kmm.notes.entity.Note

class DatabaseImpl(
    databaseDriverFactory: DatabaseDriverFactory
) : Database {

    private val appDatabase by lazy {
        AppDatabase(databaseDriverFactory.createDriver())
    }

    private val dbQuery = appDatabase.appDatabaseQueries

    override suspend fun getAllNotes(): List<Note> {
        return dbQuery.selectAllNotes { id, title, body ->
            Note(id, title, body)
        }.executeAsList()
    }

    override suspend fun insertNote(
        title: String,
        body: String
    ) {
        dbQuery.transaction {
            dbQuery.insertNote(title, body)
        }
    }

    override suspend fun deleteNote(noteId: Long) {
        dbQuery.deleteNoteById(noteId)
    }

    override suspend fun getNoteById(noteId: Long): Note? {
        return dbQuery.selectNoteById(noteId) { id, title, body ->
            Note(id, title, body)
        }.executeAsOneOrNull()
    }
}