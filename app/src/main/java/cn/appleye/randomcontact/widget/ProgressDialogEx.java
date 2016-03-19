package cn.appleye.randomcontact.widget;

import java.text.NumberFormat;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.appleye.randomcontact.R;

public class ProgressDialogEx extends AlertDialog{
	
	/** Creates a ProgressDialog with a circular, spinning progress
     * bar. This is the default.
     */
    public static final int STYLE_SPINNER = 0;
    
    /** Creates a ProgressDialog with a horizontal progress bar.
     */
    public static final int STYLE_HORIZONTAL = 1;
    
    private ProgressBar mProgress;
    private TextView mMessageView;
    
    private int mProgressStyle = STYLE_SPINNER;
    private TextView mProgressNumber;
    private String mProgressNumberFormat;
    private TextView mProgressPercent;
    private TextView mProgressMessage;
    private NumberFormat mProgressPercentFormat;
    
    private int mMax;
    private int mProgressVal;
    private CharSequence mMessage;
    private boolean mIndeterminate;
    
    private boolean mHasStarted;
    private Handler mViewUpdateHandler;

	public ProgressDialogEx(Context context) {
		super(context);
		initFormats();
	}
	
	public static ProgressDialogEx show(Context context, CharSequence title,
            CharSequence message) {
        return show(context, title, message, false);
    }

    public static ProgressDialogEx show(Context context, CharSequence title,
            CharSequence message, boolean indeterminate) {
        return show(context, title, message, indeterminate, false, null);
    }

    public static ProgressDialogEx show(Context context, CharSequence title,
            CharSequence message, boolean indeterminate, boolean cancelable) {
        return show(context, title, message, indeterminate, cancelable, null);
    }

    public static ProgressDialogEx show(Context context, CharSequence title,
            CharSequence message, boolean indeterminate,
            boolean cancelable, OnCancelListener cancelListener) {
    	ProgressDialogEx dialog = new ProgressDialogEx(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setIndeterminate(indeterminate);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
        dialog.show();
        return dialog;
    }
	
	private void initFormats() {
        mProgressNumberFormat = "%1d/%2d";
        mProgressPercentFormat = NumberFormat.getPercentInstance();
        mProgressPercentFormat.setMaximumFractionDigits(0);
    }
	
	 @Override
	  protected void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 
		 setContentView(R.layout.layout_progress_dialog_ex);
		 
		 mProgress = (ProgressBar) findViewById(R.id.progress);
         mProgressNumber = (TextView) findViewById(R.id.progress_number);
         mProgressPercent = (TextView) findViewById(R.id.progress_percent);
         mProgressMessage=(TextView) findViewById(R.id.progress_message);
		 
		 if (mProgressStyle == STYLE_HORIZONTAL) {
			 /* Use a separate handler to update the text views as they
	             * must be updated on the same thread that created them.
	             */
	            mViewUpdateHandler = new Handler() {
	                @Override
	                public void handleMessage(Message msg) {
	                    super.handleMessage(msg);
	                    
	                    /* Update the number and percent */
	                    int progress = mProgress.getProgress();
	                    int max = mProgress.getMax();
	                    if (mProgressNumberFormat != null) {
	                        String format = mProgressNumberFormat;
	                        mProgressNumber.setText(String.format(format, progress, max));
	                    } else {
	                        mProgressNumber.setText("");
	                    }
	                    if (mProgressPercentFormat != null) {
	                        double percent = (double) progress / (double) max;
	                        SpannableString tmp = new SpannableString(mProgressPercentFormat.format(percent));
	                        tmp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
	                                0, tmp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	                        mProgressPercent.setText(tmp);
	                    } else {
	                        mProgressPercent.setText("");
	                    }
	                }
	            };
		 } else{
			 mProgressPercent.setText("");
		 }
		 
		 if (mMax > 0) {
	         setMax(mMax);
	     }
		 
		 if (mProgressVal > 0) {
            setProgress(mProgressVal);
         }
		 
		 if (mMessage != null) {
            setMessage(mMessage);
         }
		 
         setIndeterminate(mIndeterminate);
	 }
	 
	@Override
    public void onStart() {
        super.onStart();
        mHasStarted = true;
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        mHasStarted = false;
    }
    
    public void setProgress(int value) {
        if (mHasStarted) {
            mProgress.setProgress(value);
            onProgressChanged();
        } else {
            mProgressVal = value;
        }
    }
    
    public int getProgress() {
        if (mProgress != null) {
            return mProgress.getProgress();
        }
        return mProgressVal;
    }
    
    public int getMax() {
        if (mProgress != null) {
            return mProgress.getMax();
        }
        return mMax;
    }

    public void setMax(int max) {
        if (mProgress != null) {
            mProgress.setMax(max);
            onProgressChanged();
        } else {
            mMax = max;
        }
    }
    
    public void setIndeterminate(boolean indeterminate) {
        if (mProgress != null) {
            mProgress.setIndeterminate(indeterminate);
        } else {
            mIndeterminate = indeterminate;
        }
    }

    public boolean isIndeterminate() {
        if (mProgress != null) {
            return mProgress.isIndeterminate();
        }
        return mIndeterminate;
    }
    
    @Override
    public void setMessage(CharSequence message) {
    	if(mProgressMessage!=null){
	      mProgressMessage.setText(message);
	    }
	    else{
	      mMessage = message;
	    }
    }
    
    public void setProgressStyle(int style) {
        mProgressStyle = style;
    }

    /**
     * Change the format of the small text showing current and maximum units
     * of progress.  The default is "%1d/%2d".
     * Should not be called during the number is progressing.
     * @param format A string passed to {@link String#format String.format()};
     * use "%1d" for the current number and "%2d" for the maximum.  If null,
     * nothing will be shown.
     */
    public void setProgressNumberFormat(String format) {
        mProgressNumberFormat = format;
        onProgressChanged();
    }

    /**
     * Change the format of the small text showing the percentage of progress.
     * The default is
     * {@link NumberFormat#getPercentInstance() NumberFormat.getPercentageInstnace().}
     * Should not be called during the number is progressing.
     * @param format An instance of a {@link NumberFormat} to generate the
     * percentage text.  If null, nothing will be shown.
     */
    public void setProgressPercentFormat(NumberFormat format) {
        mProgressPercentFormat = format;
        onProgressChanged();
    }
    
    private void onProgressChanged() {
        if (mProgressStyle == STYLE_HORIZONTAL) {
            if (mViewUpdateHandler != null && !mViewUpdateHandler.hasMessages(0)) {
                mViewUpdateHandler.sendEmptyMessage(0);
            }
        }
    }
}
