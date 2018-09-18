package com.toddywannalearn.roomsample.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.toddywannalearn.roomsample.R
import com.toddywannalearn.roomsample.data.Word
import java.util.*


class WordListAdapter : RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView

        init {
            wordItemView = itemView!!.findViewById(R.id.textview)
        }
    }

    private val mInflater: LayoutInflater
    private var mWords: List<Word> = Collections.emptyList()

    constructor(context: Context){
        mInflater = LayoutInflater.from(context)
    }

    fun setWords(words: List<Word>){
        mWords = words
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        var view: View = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mWords.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        var current: Word = mWords[position]
        holder.wordItemView.text = current.mWord
    }

}