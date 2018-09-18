package com.toddywannalearn.roomsample.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask


@Database(entities = [Word::class],version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao


    // Makes the wordRoomDatabase a singleton, preventing it of having multiple instances opened at the same time

    companion object {
        @Volatile private var instance: WordRoomDatabase? = null

        fun getInstance(context: Context): WordRoomDatabase? {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        // Creates the database
        private fun buildDatabase(context: Context): WordRoomDatabase{
            return Room.databaseBuilder(context.applicationContext,WordRoomDatabase::class.java, "word_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback(){
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            PopulateDbAsync(instance).execute()
                        }
                    })
                    .build()
        }
    }

    class PopulateDbAsync : AsyncTask<Void, Void, Void>{

        private val mDao: WordDao

        constructor(db: WordRoomDatabase?){
            mDao = db!!.wordDao()
        }

        override fun doInBackground(vararg params: Void): Void? {
            mDao.deleteAll()
            var word = Word("Hello")
            mDao.insert(word)
            word = Word("Sabrina")
            mDao.insert(word)
            word = Word("Amo")
            mDao.insert(word)
            return null
        }
    }

}