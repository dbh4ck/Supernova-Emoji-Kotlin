package com.db.supernova_emoji_kotlin.Helper

import android.content.Context
import com.db.supernova_emoji_kotlin.Emoji.Emojicon
import android.widget.GridView
import com.db.supernova_emoji_kotlin.R

/**
 * Created by DB on 06-01-2018.
 */
class EmojiconRecentsGridView(context: Context, emojicons: Array<Emojicon>?, recents: EmojiconRecents, emojiconsPopup: EmojiconsPopup, useSystemDefault: Boolean) : EmojiconGridView(context, emojicons, recents, emojiconsPopup, useSystemDefault), EmojiconRecents {
    var mAdapter: EmojiAdapter? = null
    private var mUseSystemDefault = useSystemDefault

    init {
        val recents1 = EmojiconRecentsManager.getInstance(rootView!!.getContext())
        mAdapter = EmojiAdapter(rootView!!.getContext(), recents1, mUseSystemDefault)
        mAdapter!!.setEmojiClickListener(object : EmojiconGridView.OnEmojiconClickedListener {

            override fun onEmojiconClicked(emojicon: Emojicon) {
                if (mEmojiconPopup!!.onEmojiconClickedListener != null) {
                    mEmojiconPopup!!.onEmojiconClickedListener!!.onEmojiconClicked(emojicon)
                }
            }
        })
        val gridView = rootView!!.findViewById(R.id.Emoji_GridView) as GridView
        gridView.adapter = mAdapter
        if (mAdapter != null)
            mAdapter!!.notifyDataSetChanged()
    }

    override fun addRecentEmoji(context: Context, emojicon: Emojicon) {
        val recents = EmojiconRecentsManager
                .getInstance(context)
        recents.push(emojicon)

        // notify dataset changed
        if (mAdapter != null)
            mAdapter!!.notifyDataSetChanged()
    }
}