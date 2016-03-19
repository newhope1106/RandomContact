package cn.appleye.randomcontact;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import cn.appleye.randomcontact.common.ListAdapter;

/**
 * Created by iSpace on 2016/3/19.
 */
public class ContactsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private EditText mSearchView;
    private ListView mListView;
    private ListAdapter mAdapter;
    private View mCreateContactsView;
    private View mContainerView;

    private String mLastQueryString = "";
    private static final int LOADER_ID = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflateView(inflater, container);
    }

    protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.content_random, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View rootView = getView();

        mSearchView = (EditText)rootView.findViewById(R.id.search_view);

        mAdapter = new ListAdapter(getActivity());
        mListView = (ListView)rootView.findViewById(R.id.list_view);
        mCreateContactsView = rootView.findViewById(R.id.generate_contacts);
        mContainerView = rootView.findViewById(R.id.container_view);
        mListView.setEmptyView(mCreateContactsView);

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
        return new CursorLoader(getActivity(), null, null, null, null, null);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args){
        CursorLoader cursorLoader = createCursorLoader();
        mAdapter.configureLoader(cursorLoader);

        return cursorLoader;
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
