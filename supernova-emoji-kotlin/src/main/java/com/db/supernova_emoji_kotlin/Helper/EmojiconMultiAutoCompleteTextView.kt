package com.db.supernova_emoji_kotlin.Helper

import android.content.Context
import android.util.AttributeSet
import android.widget.MultiAutoCompleteTextView
import android.text.style.DynamicDrawableSpan
import com.db.supernova_emoji_kotlin.Emoji.Emojicon
import android.content.res.TypedArray
import com.db.supernova_emoji_kotlin.R


/**
 * Created by DB on 06-01-2018.
 */
class EmojiconMultiAutoCompleteTextView: MultiAutoCompleteTextView {

    private var mEmojiconSize: Int = 0
    private var mEmojiconAlignment: Int = 0
    private var mEmojiconTextSize: Int = 0
    private var mUseSystemDefault = false

    constructor (context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs)
    }

    constructor (context: Context, attrs: AttributeSet) : super(context, attrs, android.R.attr.editTextStyle) {
        init(attrs)
    }

    constructor (context: Context) : super(context) {
        mEmojiconSize = textSize.toInt()
        mEmojiconTextSize = textSize.toInt()
    }

    private fun init(attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.Emojicon)
        mEmojiconSize = a.getDimension(R.styleable.Emojicon_emojiconSize, textSize).toInt()
        mEmojiconAlignment = a.getInt(R.styleable.Emojicon_emojiconAlignment, DynamicDrawableSpan.ALIGN_BASELINE)
        mUseSystemDefault = a.getBoolean(R.styleable.Emojicon_emojiconUseSystemDefault, false)
        a.recycle()
        mEmojiconTextSize = textSize.toInt()
        setText(text)
    }

    override fun onTextChanged(text: CharSequence, start: Int, lengthBefore: Int, lengthAfter: Int) {
        updateText()
    }

    /**
     * Set the size of emojicon in pixels.
     */
    fun setEmojiconSize(pixels: Int) {
        mEmojiconSize = pixels

        updateText()
    }

    private fun updateText() {
        EmojiconHandler.addEmojis(context, text, mEmojiconSize, mEmojiconAlignment, mEmojiconTextSize, mUseSystemDefault)
    }

    /**
     * Set whether to use system default emojicon
     */
    fun setUseSystemDefault(useSystemDefault: Boolean) {
        mUseSystemDefault = useSystemDefault
    }
}