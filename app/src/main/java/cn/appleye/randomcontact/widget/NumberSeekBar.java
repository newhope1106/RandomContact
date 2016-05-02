package cn.appleye.randomcontact.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * Created by feiyu on 2016/5/1.
 */
public class NumberSeekBar extends SeekBar {
    private SeekBarThumb mThumb;

    public NumberSeekBar(Context context) {
        super(context);
        mThumb = new SeekBarThumb(context);
        setThumb(mThumb);
        setThumbOffset(mThumb.getIntrinsicWidth());
    }

    public NumberSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mThumb = new SeekBarThumb(context);
        setThumb(mThumb);
        setThumbOffset(mThumb.getIntrinsicWidth());
    }

    public NumberSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mThumb = new SeekBarThumb(context);
        setThumb(mThumb);
        setThumbOffset(mThumb.getIntrinsicWidth());
    }

    @Override
    public void setThumb(Drawable thumb) {
        super.setThumb(thumb);
    }

    //设置thumb的偏移数值
    @Override
    public void setThumbOffset(int thumbOffset) {
        // TODO Auto-generated method stub
        super.setThumbOffset(thumbOffset / 10);
    }
}
