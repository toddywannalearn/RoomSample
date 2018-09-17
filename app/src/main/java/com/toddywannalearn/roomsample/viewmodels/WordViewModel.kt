package com.toddywannalearn.roomsample.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.toddywannalearn.roomsample.data.Word
import com.toddywannalearn.roomsample.data.WordRepository


class WordViewModel : AndroidViewModel {

    private val mRepository: WordRepository
    private var mAllWords: LiveData<List<Word>>

    constructor(application: Application):super(application){
        mRepository = WordRepository(application)
        mAllWords = mRepository.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word>>{
        return mAllWords
    }

    fun insert(word: Word) = mRepository.insert(word)
}