package com.kmm.notes.entity.cache

import com.kmm.notes.entity.Note

interface Database {

    suspend fun getAllNotes(): List<Note>

    suspend fun insertNote(
        title: String,
        body: String
    )

    suspend fun deleteNote(noteId: Long)

    suspend fun getNoteById(noteId: Long): Note?
}
