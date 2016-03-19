package cn.appleye.randomcontact.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import cn.appleye.randomcontact.R;

public class SettingsListItemView extends TextView{
	
	private boolean mIsChecked = false;
	private Drawable mCheckDrawable;
	
	private int mCheckWidth;
	private int mCheckHeight;
	
	private Context mContext;
	
	public SettingsListItemView(Context context) {
		super(context);
		
		mContext = context;
	}
	
	public SettingsListItemView(Context context, AttributeSet attrs){
		super(context, attrs);
		
		mContext = context;
	}
	
	public SettingsListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		mContext = context;
	}
	
	public void setChecked(boolean isChecked) {
		mIsChecked = isChecked;
		
		if (mIsChecked) {
			mCheckDrawable = mContext.getResources().getDrawable(R.drawable.btn_check_on);
		} else {
			mCheckDrawable = mContext.getResources().getDrawable(R.drawable.btn_check_off);
		}
		
		mCheckWidth = mCheckDrawable.getIntrinsicWidth();
		mCheckHeight = mCheckDrawable.getIntrinsicHeight();
		
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
		if (mCheckDrawable != null) {
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
		if (mCheckDrawable != null) {
			mCheckDrawable.draw(canvas);
		}
		
		super.dispatchDraw(canvas);
	}
}
