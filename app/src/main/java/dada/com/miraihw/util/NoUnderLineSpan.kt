package dada.com.miraihw.util

import android.os.Parcel
import android.text.TextPaint
import android.text.style.UnderlineSpan

class NoUnderlineSpan : UnderlineSpan {
    constructor() {}
    constructor(src: Parcel?) {}

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = false
    }
}