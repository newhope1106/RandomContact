package cn.appleye.randomcontact;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class AdvancedSettingsActivity extends Activity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_settings);

        mListView = (ListView) findViewById(R.id.advanced_settings_list);
    }

    private class Entry{
        private String mTitle;
        private String mMinValueLabel;
        private String mMaxValueLabel;
        private int mMinValue;
        private int mMaxValue;

        public Entry(String title, String minValueLabel, String maxValueLabel) {
            this(title, minValueLabel, maxValueLabel, 1, 1);
        }

        public Entry(String title, String minValueLabel, String maxValueLabel, int minValue, int maxValue) {
            mTitle = title;
            mMinValueLabel = minValueLabel;
            mMaxValueLabel = maxValueLabel;
            mMinValue = minValue;
            mMaxValue = maxValue;
        }
    }

}
