package com.db.supernova_emoji_kotlin.Helper

import android.content.Context
import com.db.supernova_emoji_kotlin.Emoji.Emojicon

/**
 * Created by DB on 06-01-2018.
 */
interface EmojiconRecents {
    fun addRecentEmoji(context: Context, emojicon: Emojicon)
}