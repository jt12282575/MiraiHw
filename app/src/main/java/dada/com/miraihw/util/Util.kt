package dada.com.miraihw.util

import android.os.Parcel
import android.text.TextPaint
import android.text.style.UnderlineSpan


class Util {
    companion object{
        fun getEmoji(unicode: Int): String {
            return String(Character.toChars(unicode))
        }
    }
}