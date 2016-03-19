package cn.appleye.randomcontact.widget;

/**
 * Created by iSpace on 2016/3/19.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BorderLinearLayout extends LinearLayout{

    private Paint mPaint = null;
    private int mColor = 0xffe2e2e2;
    private int mStrokeWidth = 2;

    public BorderLinearLayout(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public BorderLinearLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        setWillNotDraw(false);
    }

    public BorderLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setStyle(Style.STROKE);
            mPaint.setColor(mColor);
            mPaint.setStrokeWidth(mStrokeWidth);
        }

        int width = getWidth();
        int height = getHeight();

        canvas.drawLine(0, 0, width, 0, mPaint);
        canvas.drawLine(0, height, width, height, mPaint);

        super.onDraw(canvas);
    }
}
