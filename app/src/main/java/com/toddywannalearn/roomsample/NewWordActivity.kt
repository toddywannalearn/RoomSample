package com.toddywannalearn.roomsample

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_new_word.*

class NewWordActivity : AppCompatActivity() {

    val extra_reply: String = "com.example.android.wordlistsql.REPLY"
    private lateinit var mEditWordView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        mEditWordView = findViewById(R.id.edit_word)

        button_save.setOnClickListener {
            val replyIntent = Intent()
            if(TextUtils.isEmpty(mEditWordView.text))
                setResult(Activity.RESULT_CANCELED, replyIntent)
            else{
                var word: String = mEditWordView.text.toString()
                replyIntent.putExtra(extra_reply, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}
