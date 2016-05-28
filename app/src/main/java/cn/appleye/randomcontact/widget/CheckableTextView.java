package cn.appleye.randomcontact.widget;

import cn.appleye.randomcontact.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

public class CheckableTextView extends TextView{
	private int mNormalColor = 0xffe0eef1;
	
	private boolean mIsChecked = false;
	private Context mContext;
	private Drawable mCheckDrawable;
	
	private int mCheckWidth;
	private int mCheckHeight;
	
	public CheckableTextView(Context context) {
		super(context);
		setBackgroundColor(mNormalColor);
		
		mContext = context;
	}
	
	public CheckableTextView(Context context, AttributeSet attrs){
		super(context, attrs);
		setBackgroundColor(mNormalColor);
		
		mContext = context;
	}
	
	public CheckableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setBackgroundColor(mNormalColor);
		
		mContext = context;
	}
	
	public void setChecked(boolean isChecked) {
		mIsChecked = isChecked;

		if (mIsChecked) {
			mCheckDrawable = mContext.getResources().getDrawable(R.drawable.btn_check_on);
			mCheckWidth = mCheckDrawable.getIntrinsicWidth();
			mCheckHeight = mCheckDrawable.getIntrinsicHeight();
		} else {
			mCheckDrawable = null;
		}
		
		requestLayout();
	}
	
	public boolean isChecked() {
		return mIsChecked;
	}
	
	public void toggole() {
		setChecked(!mIsChecked);
	}
	
	@Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		if (mIsChecked && mCheckDrawable!=null) {
			int width = right-left;
			int height = bottom-top;
			mCheckDrawable.setBounds(width - mCheckWidth, (height - mCheckHeight)/2, width, (height + mCheckHeight)/2);
		}
		
		super.onLayout(changed, left, top, right, bottom);
	}
	
	/**
	 * we will draw the checked image here
	 * */
	@Override
	protected void dispatchDraw(Canvas canvas) {
		
		if (mIsChecked && mCheckDrawable!=null) {
			mCheckDrawable.draw(canvas);
		}
		
		super.dispatchDraw(canvas);
	}
}
