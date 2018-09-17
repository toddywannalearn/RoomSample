package com.toddywannalearn.roomsample.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


@Database(entities = [Word::class],version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    @Volatile private var instance: WordRoomDatabase? = null

    // Makes the wordRoomDatabase a singleton, preventing it of having multiple instances opened at the same time
    fun getInstance(context: Context): WordRoomDatabase? {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }

    // Creates the database
    private fun buildDatabase(context: Context): WordRoomDatabase{
        return Room.databaseBuilder(context.applicationContext,WordRoomDatabase::class.java, "word_database").build()
    }



}