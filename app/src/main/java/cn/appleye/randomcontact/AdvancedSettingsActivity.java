package cn.appleye.randomcontact;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.appleye.randomcontact.utils.SettingsUtils;
import cn.appleye.randomcontact.widget.CirclSeekDialog;
import cn.appleye.randomcontact.widget.CircleSeekBar;

public class AdvancedSettingsActivity extends Activity {
    private ListView mListView;
    private ArrayAdapter<Entry> mAdapter ;
    private SharedPreferences mPrefs;

    private LayoutInflater mInflater;

    private ArrayList<Entry> mEntries = new ArrayList<Entry>();

    private CirclSeekDialog mDialog = null;
    private CircleSeekBar mCircleSeekBar;

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

        mEntries.add(new Entry(SettingsUtils.PER_KEY_DISPLAY_NAME_MIN_LENGTH,
                getString(R.string.label_display_name), getString(R.string.min_length), true, 3, 100));
        mEntries.add(new Entry(SettingsUtils.PER_KEY_DISPLAY_NAME_MAX_LENGTH,
                getString(R.string.label_display_name), getString(R.string.max_length), false, 3, 100));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_PHNONE_NUMBER_MIN_COUNT,
                getString(R.string.label_phone_number), getString(R.string.min_count), true));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_PHNONE_NUMBER_MAX_COUNT,
                getString(R.string.label_phone_number), getString(R.string.max_count), false));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_EVENT_MIN_COUNT,
                getString(R.string.label_event), getString(R.string.min_count), true));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_EVENT_MAX_COUNT,
                getString(R.string.label_event), getString(R.string.max_count), false));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_EMAIL_MIN_COUNT,
                getString(R.string.label_email), getString(R.string.min_count), true));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_EMAIL_MAX_COUNT,
                getString(R.string.label_email), getString(R.string.max_count), false));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_POSTAL_MIN_COUNT,
                getString(R.string.label_postal), getString(R.string.min_count), true));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_POSTAL_MAX_COUNT,
                getString(R.string.label_postal), getString(R.string.max_count), false));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_IM_MIN_COUNT,
                getString(R.string.label_im), getString(R.string.min_count), true));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_IM_MAX_COUNT,
                getString(R.string.label_im), getString(R.string.max_count), false));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_ORG_MIN_COUNT,
                getString(R.string.label_org), getString(R.string.min_count), true));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_ORG_MAX_COUNT,
                getString(R.string.label_org), getString(R.string.max_count), false));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_NOTE_MIN_COUNT,
                getString(R.string.label_note), getString(R.string.min_count), true));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_NOTE_MAX_COUNT,
                getString(R.string.label_note), getString(R.string.max_count), false));

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_WEBSITE_MIN_COUNT,
                getString(R.string.label_web_site), getString(R.string.min_count), true));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_WEBSITE_MAX_COUNT,
                getString(R.string.label_web_site), getString(R.string.max_count), false));

        for(Entry entry : mEntries) {
            entry.setValue(mPrefs.getInt(entry.getKey(), entry.getValue()));
        }
    }

    private void setupListView(){
        mAdapter = new ArrayAdapter<Entry>(this, 0, mEntries){

            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                boolean useCache = true;
                if (convertView == null) {
                    useCache = false;
                    convertView = mInflater.inflate(R.layout.advanced_settings_list_item_view, null);
                }

                Entry entry = mEntries.get(position);

                TextView titleView = (TextView)convertView.findViewById(R.id.item_title);
                if (entry.isFirst()) {
                    titleView.setText(entry.getTitle());
                    titleView.setVisibility(View.VISIBLE);
                } else {
                    titleView.setVisibility(View.GONE);
                }

                TextView labelView = (TextView)convertView.findViewById(R.id.item_label);
                labelView.setText(entry.getValueLabel());
                TextView editView = (TextView)convertView.findViewById(R.id.item_edit);
                editView.setText(entry.getValue() + "");
                editView.setTag(entry);

                if (!useCache) {
                    editView.setOnClickListener(new EditViewOnClickListener());
                }

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

    private class EditViewOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Entry entry = (Entry) view.getTag();

            if (entry != null) {
                showSeekBarDialog(entry);
            }
        }
    }

    private void showSeekBarDialog(Entry entry) {
        if (mDialog == null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View contentView = inflater.inflate(R.layout.circle_seek_bar_layout, null);
            mCircleSeekBar = (CircleSeekBar) contentView.findViewById(R.id.circle_seekbar);

            CirclSeekDialog.Builder builder = new CirclSeekDialog.Builder(this);
            builder.setContentView(contentView);
            //builder.setMessage("这个就是自定义的提示框");
            builder.setTitle(getString(R.string.circle_seek_dialog_title));
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (mCircleSeekBar != null) {
                        Entry entry = (Entry) mCircleSeekBar.getTag();
                        entry.setValue(mCircleSeekBar.getProgress());
                        mAdapter.notifyDataSetChanged();
                    }
                }
            });

            builder.setNegativeButton("取消",
                    new android.content.DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            mDialog = builder.create();
        }

        if (mCircleSeekBar != null) {
            mCircleSeekBar.setProgressMax(entry.getLimitValue()+1);
            mCircleSeekBar.setProgress(entry.getValue());
            mCircleSeekBar.setTag(entry);
        }

        mDialog.show();
    }

    private void savePreference() {
        SharedPreferences.Editor editor = mPrefs.edit();
        for (Entry entry : mEntries) {
            editor.putInt(entry.getKey(), entry.getValue());
        }

        editor.commit();

        Toast.makeText(getApplicationContext(), R.string.save_success, Toast.LENGTH_SHORT).show();

        finish();
    }

    private class Entry{
        private String mKey;
        private String mTitle;
        private String mValueLabel;
        private int mValue;
        private int mLimitValue;
        private boolean mIsFirst;

        public Entry(String key, String title, String valueLabel, boolean isFirst) {
            this(key, title, valueLabel, isFirst, 1, 5);
        }

        public Entry(String key, String title, String valueLabel, boolean isFirst, int value, int limitValue) {
            mKey = key;
            mTitle = title;
            mValueLabel = valueLabel;
            mIsFirst = isFirst;
            mValue = value;
            mLimitValue = limitValue;
        }

        public String getKey() {
            return mKey;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getValueLabel() {
            return mValueLabel;
        }

        public boolean isFirst() {
            return mIsFirst;
        }

        public void setValue(int value) {
            mValue = value;
        }

        public int getValue() {
            return mValue;
        }

        public int getLimitValue() {
            return mLimitValue;
        }
    }

}
