package com.toddywannalearn.roomsample.data

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class WordRepository {
    private var mWordDao: WordDao
    private var mAllWords: LiveData<List<Word>>

    constructor(application: Application) {
        val db: WordRoomDatabase? = WordRoomDatabase.getInstance(application)
        mWordDao = db!!.wordDao()
        mAllWords = mWordDao.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    fun insert(word: Word) {
        insertAsyncTask(mWordDao).execute(word)
    }

    private class insertAsyncTask : AsyncTask<Word,Void,Void>{

        private var mAsyncTaskDao: WordDao

        constructor(dao: WordDao){
            mAsyncTaskDao = dao
        }

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.insert(params[0])
            //params[0]?.let { mAsyncTaskDao.insert(it) }
            return null
        }
    }
}