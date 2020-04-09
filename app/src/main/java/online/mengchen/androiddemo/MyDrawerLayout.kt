package online.mengchen.androiddemo

import android.content.Context
import android.util.AttributeSet
import androidx.drawerlayout.widget.DrawerLayout

class MyDrawerLayout(context: Context, attrs: AttributeSet?): DrawerLayout(context, attrs) {

    constructor(context: Context): this(context, null)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val nWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
            MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY
        )
        val nHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
            MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY
        )
        super.onMeasure(nWidthMeasureSpec, nHeightMeasureSpec)
    }

}