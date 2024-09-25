package com.kmm.notes

import com.kmm.notes.cache.AndroidDatabaseDriverFactory
import com.kmm.notes.entity.cache.Database
import com.kmm.notes.entity.cache.DatabaseDriverFactory
import com.kmm.notes.entity.cache.DatabaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { androidContext() }

    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(get()) }

    single<Database> { DatabaseImpl(get()) }
}