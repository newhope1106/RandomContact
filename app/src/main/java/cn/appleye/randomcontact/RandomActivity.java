package cn.appleye.randomcontact;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import cn.appleye.randomcontact.common.ListAdapter;

public class RandomActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText mSearchView;
    private ListView mListView;
    private ListAdapter mAdapter;
    private View mCreateContactsView;
    private View mContainerView;

    private String mLastQueryString = "";
    private static final int LOADER_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        mSearchView = (EditText)findViewById(R.id.search_view);

        mAdapter = new ListAdapter(this);
        mListView = (ListView)findViewById(R.id.list_view);
        mCreateContactsView = findViewById(R.id.generate_contacts);
        mContainerView = findViewById(R.id.container_view);
        mListView.setEmptyView(mCreateContactsView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setupSearchView();
        setupListView();

        startLoading();
    }

    private void setupSearchView() {
        mSearchView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                startQuery();
            }
        });
    }

    private void setupListView() {
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = (Uri)mAdapter.getItem(position);

                if (uri != null) {
                    final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });
    }

    private void startLoading() {
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    private void startQuery() {
        String queryString = mSearchView.getText().toString();
        mAdapter.setQueryString(queryString);

        if ((mLastQueryString==null && queryString == mLastQueryString) || queryString.equals(mLastQueryString)) {

        } else {
            getLoaderManager().restartLoader(LOADER_ID, null, this);
        }

        mLastQueryString = queryString;
    }

    private CursorLoader createCursorLoader() {
        return new CursorLoader(this, null, null, null, null, null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args){
        CursorLoader cursorLoader = createCursorLoader();
        mAdapter.configureLoader(cursorLoader);

        return cursorLoader;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_random, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        mAdapter.changeCursor(data);

        if (!mAdapter.isSearchMode() && (data ==null || data.getCount() == 0)) {
            mContainerView.setVisibility(View.GONE);
        } else {
            mContainerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
