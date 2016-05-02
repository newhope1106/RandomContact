package cn.appleye.randomcontact.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import cn.appleye.randomcontact.R;

/**
 * Created by feiyu on 2016/5/2.
 */
public class SeekBarThumb extends Drawable{
    private Paint mTextPaint;
    private Paint mCirclePaint;
    private int mTextSize = 40;
    private int mValue=1;
    private int mCircleSize;

    public SeekBarThumb(Context context){
        mTextPaint = new Paint();
        mTextPaint.setTypeface(Typeface.DEFAULT);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(0xff23fc4f);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(10);

        mCircleSize = (int)context.getResources().getDimension(R.dimen.seek_bar_thumb_size);
    }

    @Override
    public int getIntrinsicWidth()
    {
        return mCircleSize;
    }

    @Override
    public int getIntrinsicHeight()
    {
        return mCircleSize;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mCircleSize/2, mCircleSize/2, mCircleSize/2, mCirclePaint);
        canvas.drawText(mValue+"", 0, 0, mTextPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mTextPaint.setAlpha(alpha);
        mCirclePaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mTextPaint.setColorFilter(colorFilter);
        mCirclePaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public void setValue(int value) {
        mValue = value;
    }

    public void updateValue(int value) {
        mValue = value;
        invalidateSelf();
    }
}
