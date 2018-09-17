package com.toddywannalearn.roomsample.data

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class WordRepository private constructor(
        private var mWordDao: WordDao,
        private var mAllWords: LiveData<List<Word>>
){

    fun getAllWords(): LiveData<List<Word>> {
        return mAllWords
    }

    fun insert(word: Word) {}

    private class insertAsyncTask : AsyncTask<Word,Void,Void>{

        private var mAsyncTaskDao: WordDao

        constructor(dao: WordDao){
            mAsyncTaskDao = dao
        }

        override fun doInBackground(vararg params: Word?): Void? {
            params[0]?.let { mAsyncTaskDao.insert(it) }
            return null
        }
    }
}