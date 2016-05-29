package cn.appleye.randomcontact;

/**
 * Created by iSpace on 2016/3/19.
 */
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;
import cn.appleye.randomcontact.utils.SettingsUtils;
import cn.appleye.randomcontact.widget.SettingsListItemView;

public class SettingsActivity extends Activity{
    private ListView mListView;

    private ArrayList<Entry> mEntries = new ArrayList<Entry>();

    private ArrayAdapter<Entry> mAdapter ;

    private LayoutInflater mInflater;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_settings_activity);

        mListView = (ListView) findViewById(R.id.settings_list);

        mInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        initPreference();
        setupListView();
    }

    private void initPreference() {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        mEntries.add(new Entry(SettingsUtils.PRE_KEY_DISPLAY_NAME, getString(R.string.generate_display_name), true));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_NICK_NAME, getString(R.string.generate_nick_name), false));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_PNONE_NUMBER, getString(R.string.generate_phone_number), true));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_EVENT, getString(R.string.generate_event_number), false));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_EMAIL, getString(R.string.generate_email), false));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_POSTAL, getString(R.string.generate_postal), false));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_IM, getString(R.string.generate_im), false));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_ORG, getString(R.string.generate_org), false));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_PHOTO, getString(R.string.generate_photo), false));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_NOTE, getString(R.string.generate_note), false));
        mEntries.add(new Entry(SettingsUtils.PRE_KEY_WEBSITE, getString(R.string.generate_web_site), false));

        for (Entry entry : mEntries) {
            boolean value = mPrefs.getBoolean(entry.getKey(), entry.isChecked());
            entry.setChecked(value);
        }
    }

    private void savePreference() {
        Editor editor = mPrefs.edit();
        for (Entry entry : mEntries) {
            editor.putBoolean(entry.getKey(), entry.isChecked());
        }

        editor.commit();

        Toast.makeText(getApplicationContext(), R.string.save_success, Toast.LENGTH_SHORT).show();

        finish();
    }

    private void setupListView() {

        mAdapter = new ArrayAdapter<Entry>(this, 0, mEntries){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                SettingsListItemView checkableTextView;
                if (convertView == null) {
                    checkableTextView = (SettingsListItemView)mInflater.inflate(R.layout.setting_list_item_view, null);
                    checkableTextView.setTextColor(0xFF627D9B);
                } else {
                    checkableTextView = (SettingsListItemView)convertView;
                }

                Entry entry = mEntries.get(position);

                checkableTextView.setText(entry.getTitle());
                checkableTextView.setChecked(entry.isChecked());

                return checkableTextView;
            }
        };

        mListView.setAdapter(mAdapter);
        mListView.setDivider(new ColorDrawable(0xfff6f9fe));
        mListView.setDividerHeight((int)getResources().getDimension(R.dimen.divider_height));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SettingsListItemView checkableTextView = (SettingsListItemView) view;

                if (checkableTextView != null) {
                    checkableTextView.toggole();
                    mEntries.get(position).setChecked(checkableTextView.isChecked());
                }
            }
        });

        View footerView = mInflater.inflate(R.layout.settings_list_footer_view, null);
        mListView.addFooterView(footerView);

        footerView.findViewById(R.id.ok_btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                savePreference();
            }
        });
    }

    @Override
    public void onBackPressed(){
        //savePreference();

        finish();
    }

    private class Entry{
        private String mTitle;
        private String mKey;
        private boolean mIsChecked;

        public Entry(String key, String title, boolean isChecked) {
            mKey = key;
            mTitle = title;
            mIsChecked = isChecked;
        }

        public Entry(String key, String title) {
            this(key, title, false);
        }

        public String getTitle() {
            return mTitle;
        }

        public String getKey() {
            return mKey;
        }

        public void setChecked(boolean isChecked) {
            mIsChecked = isChecked;
        }

        public boolean isChecked() {
            return mIsChecked;
        }
    }
}
