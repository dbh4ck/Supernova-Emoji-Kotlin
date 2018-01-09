package com.db.supernova_emoji_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.db.supernova_emoji_kotlin.Actions.EmojIconActions
import com.db.supernova_emoji_kotlin.Helper.EmojiconTextView
import com.db.supernova_emoji_kotlin.Helper.EmojiconEditText
import android.widget.CheckBox
import android.widget.ImageView
import com.db.supernova_emoji_kotlin.R.id.textView
import android.widget.CompoundButton

class MainActivity : AppCompatActivity() {

    var mCheckBox: CheckBox? = null
    var emojiconEditText: EmojiconEditText? = null
    var emojiconEditText2:EmojiconEditText? = null
    var textView: EmojiconTextView? = null
    var emojiButton: ImageView? = null
    var submitButton: ImageView? = null
    var rootView: View? = null
    var emojIcon: EmojIconActions? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rootView = findViewById<View>(R.id.root_view)
        emojiButton = findViewById<View>(R.id.emoji_btn) as ImageView
        submitButton = findViewById<View>(R.id.submit_btn) as ImageView
        mCheckBox = findViewById<View>(R.id.use_system_default) as CheckBox
        emojiconEditText = findViewById<View>(R.id.emojicon_edit_text) as EmojiconEditText
        emojiconEditText2 = findViewById<View>(R.id.emojicon_edit_text2) as EmojiconEditText
        textView = findViewById<View>(R.id.textView) as EmojiconTextView
        emojIcon = EmojIconActions(applicationContext, rootView!!, emojiconEditText!!, emojiButton!!)
        emojIcon!!.ShowEmojIcon()

        emojIcon!!.setKeyboardListener(object : EmojIconActions.KeyboardListener {
            override fun onKeyboardOpen() {
                Log.e("Keyboard", "open")
            }

            override fun onKeyboardClose() {
                Log.e("Keyboard", "close")
            }
        })

        mCheckBox!!.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(compoundButton: CompoundButton?, b: Boolean) {
                emojIcon!!.setUseSystemEmoji(b);
                textView!!.setUseSystemDefault(b);
            }

        })

        emojIcon!!.addEmojiconEditTextList(emojiconEditText2!!)

        submitButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val newText = emojiconEditText!!.getText().toString()
                textView!!.setText(newText)
            }
        })
    }
}
