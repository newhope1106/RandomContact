package cn.appleye.randomcontact;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import cn.appleye.randomcontact.utils.SettingsUtils;

public class AdvancedSettingsActivity extends Activity {
    private ListView mListView;
    private ArrayAdapter<Entry> mAdapter ;
    private SharedPreferences mPrefs;

    private LayoutInflater mInflater;

    private ArrayList<Entry> mEntries = new ArrayList<Entry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_settings);

        mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mListView = (ListView) findViewById(R.id.advanced_settings_list);

        initPreference();
        setupListView();
    }

    /**
     * 初始化设置
     * */
    private void initPreference(){
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        mEntries.add(new Entry(SettingsUtils.PER_KEY_DISPLAY_NAME_MIN_LENGTH, SettingsUtils.PER_KEY_DISPLAY_NAME_MAX_LENGTH,
                getString(R.string.label_display_name), getString(R.string.min_length), getString(R.string.max_length), 3, 3, 100));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_PHNONE_NUMBER_MIN_COUNT, SettingsUtils.PRE_KEY_PHNONE_NUMBER_MAX_COUNT,
                getString(R.string.label_phone_number), getString(R.string.min_count), getString(R.string.max_count)));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_EVENT_MIN_COUNT, SettingsUtils.PRE_KEY_EVENT_MAX_COUNT,
                getString(R.string.label_event), getString(R.string.min_count), getString(R.string.max_count)));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_EMAIL_MIN_COUNT, SettingsUtils.PRE_KEY_EMAIL_MAX_COUNT,
                getString(R.string.label_email), getString(R.string.min_count), getString(R.string.max_count)));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_POSTAL_MIN_COUNT, SettingsUtils.PRE_KEY_POSTAL_MAX_COUNT,
                getString(R.string.label_postal), getString(R.string.min_count), getString(R.string.max_count)));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_IM_MIN_COUNT, SettingsUtils.PRE_KEY_IM_MAX_COUNT,
                getString(R.string.label_im), getString(R.string.min_count), getString(R.string.max_count)));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_ORG_MIN_COUNT, SettingsUtils.PRE_KEY_ORG_MAX_COUNT,
                getString(R.string.label_org), getString(R.string.min_count), getString(R.string.max_count)));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_NOTE_MIN_COUNT, SettingsUtils.PRE_KEY_NOTE_MAX_COUNT,
                getString(R.string.label_note), getString(R.string.min_count), getString(R.string.max_count)));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_WEBSITE_MIN_COUNT, SettingsUtils.PRE_KEY_WEBSITE_MAX_COUNT,
                getString(R.string.label_web_site), getString(R.string.min_count), getString(R.string.max_count)));

        for(Entry entry : mEntries) {
            entry.setMinValue(mPrefs.getInt(entry.getMinKey(), entry.getMinValue()));
            entry.setMaxValue(mPrefs.getInt(entry.getMaxKey(), entry.getMaxValue()));
        }
    }

    private void setupListView(){
        mAdapter = new ArrayAdapter<Entry>(this, 0, mEntries){

            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.advanced_settings_list_item_view, null);
                }

                Entry entry = mEntries.get(position);

                TextView titleView = (TextView)convertView.findViewById(R.id.item_title);
                titleView.setText(entry.getTitle());

                TextView minLabelView = (TextView)convertView.findViewById(R.id.item_min_label);
                minLabelView.setText(entry.getMinValueLabel());
                SeekBar minValueSeekBar = (SeekBar)convertView.findViewById(R.id.item_min_seekbar);
                minValueSeekBar.setMax(entry.getLimitValue());
                minValueSeekBar.setProgress(entry.getMinValue());

                TextView maxLabelView = (TextView)convertView.findViewById(R.id.item_max_label);
                maxLabelView.setText(entry.getMaxValueLabel());
                SeekBar maxValueSeekBar = (SeekBar)convertView.findViewById(R.id.item_max_seekbar);
                maxValueSeekBar.setMax(entry.getLimitValue());
                maxValueSeekBar.setProgress(entry.getMaxValue());

                return convertView;
            }
        };

        mListView.setAdapter(mAdapter);
        View footerView = mInflater.inflate(R.layout.settings_list_footer_view, null);
        mListView.addFooterView(footerView);

        footerView.findViewById(R.id.ok_btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                savePreference();
            }
        });
    }

    private void savePreference() {
        SharedPreferences.Editor editor = mPrefs.edit();
        for (Entry entry : mEntries) {
            editor.putInt(entry.getMinKey(), entry.getMinValue());
            editor.putInt(entry.getMaxKey(), entry.getMaxValue());
        }

        editor.commit();

        Toast.makeText(getApplicationContext(), R.string.save_success, Toast.LENGTH_SHORT).show();

        finish();
    }

    private class Entry{
        private String mMinKey;
        private String mMaxKey;
        private String mTitle;
        private String mMinValueLabel;
        private String mMaxValueLabel;
        private int mMinValue;
        private int mMaxValue;
        private int mLimitValue;

        public Entry(String minKey, String maxKey,  String title, String minValueLabel, String maxValueLabel) {
            this(minKey, maxKey, title, minValueLabel, maxValueLabel, 1, 1, 5);
        }

        public Entry(String minKey, String maxKey, String title, String minValueLabel, String maxValueLabel, int minValue, int maxValue, int limitValue) {
            mMinKey = minKey;
            mMaxKey = maxKey;
            mTitle = title;
            mMinValueLabel = minValueLabel;
            mMaxValueLabel = maxValueLabel;
            mMinValue = minValue;
            mMaxValue = maxValue;
            mLimitValue = limitValue;
        }

        public String getMinKey() {
            return mMinKey;
        }

        public String getMaxKey() {
            return mMaxKey;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getMinValueLabel() {
            return mMinValueLabel;
        }

        public void setMinValue(int minValue) {
            mMinValue = minValue;
        }

        public int getMinValue() {
            return mMinValue;
        }

        public String getMaxValueLabel() {
            return mMaxValueLabel;
        }

        public void setMaxValue(int maxValue) {
            mMaxValue = maxValue;
        }

        public int getMaxValue() {
            return mMaxValue;
        }


        public int getLimitValue() {
            return mLimitValue;
        }
    }

}
