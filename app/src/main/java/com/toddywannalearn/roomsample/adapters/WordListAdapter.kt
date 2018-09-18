package com.toddywannalearn.roomsample.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.toddywannalearn.roomsample.R
import com.toddywannalearn.roomsample.data.Word


class WordListAdapter : RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var wordItemView: TextView

        init {
            wordItemView = itemView!!.findViewById(R.id.textview)
        }
    }

    private val mInflater: LayoutInflater
    private var mWords: List<Word>

    constructor(context: Context, words: List<Word>){
        mInflater = LayoutInflater.from(context)
        mWords = words
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        var view: View = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(mWords != null)
            return mWords.size
        else return 0
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if(mWords != null){
            var current: Word = mWords[position]
            holder.wordItemView.text = current.mWord
        } else {
          holder.wordItemView.text = "No Word"
        }
    }

}