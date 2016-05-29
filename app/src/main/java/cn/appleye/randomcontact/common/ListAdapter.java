package cn.appleye.randomcontact.common;

import java.util.Arrays;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Directory;
import android.provider.ContactsContract.SearchSnippets;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import cn.appleye.randomcontact.R;

public class ListAdapter extends BaseAdapter{
	
	private static final String TAG = "ListAdapter";
	
	public static final char SNIPPET_START_MATCH = '\u0001';
    public static final char SNIPPET_END_MATCH = '\u0001';
    public static final String SNIPPET_ELLIPSIS = "\u2026";
    public static final int SNIPPET_MAX_TOKENS = 5;

    public static final String SNIPPET_ARGS = SNIPPET_START_MATCH + "," + SNIPPET_END_MATCH + ","
            + SNIPPET_ELLIPSIS + "," + SNIPPET_MAX_TOKENS;
    
    private static final String BLANK_HEADER_STRING = " ";
	
	private Cursor mCursor;
	
	private String mQueryString="";
	private boolean mSearchMode = false;
	
	private Context mContext;
	private LayoutInflater mInflater;
	
	private String[] mSections;
    private int[] mPositions;
    private int mCount;
    
    private Placement mPlacementCache = new Placement();
	
	private static final String[] CONTACTS_PROJECTION = new String[] {
			Contacts._ID,
			Contacts.DISPLAY_NAME_PRIMARY,
			Contacts.LOOKUP_KEY
	};
	
	private static final String[] FILTER_PROJECTION = new String[]{
			Contacts._ID,
			Contacts.DISPLAY_NAME_PRIMARY,
			Contacts.LOOKUP_KEY,
			SearchSnippets.SNIPPET //搜索结果部分
	};
	
	private static final int CONTACT_ID = 0;
	private static final int DISPLAY_NAME = CONTACT_ID + 1;
	private static final int LOOKUP_KEY = DISPLAY_NAME + 1;
	private static final int SNIPPET = LOOKUP_KEY + 1;
	
	public ListAdapter(Context context) {
		mContext = context;
		
		mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void changeCursor(Cursor cursor) {
		mCursor = cursor;
		
		if (mCursor != null) {
			Bundle bundle = cursor.getExtras();
			if (bundle.containsKey(Contacts.EXTRA_ADDRESS_BOOK_INDEX_TITLES)) {
				Log.d(TAG, "contain EXTRA_ADDRESS_BOOK_INDEX_TITLES");
				mSections =
	                    bundle.getStringArray(Contacts.EXTRA_ADDRESS_BOOK_INDEX_TITLES);
				int counts[] = bundle.getIntArray(Contacts.EXTRA_ADDRESS_BOOK_INDEX_COUNTS);
				
				mPositions = new int[counts.length];
		        int position = 0;
		        for (int i = 0; i < counts.length; i++) {
		            if (TextUtils.isEmpty(mSections[i])) {
		                mSections[i] = BLANK_HEADER_STRING;
		            } else if (!mSections[i].equals(BLANK_HEADER_STRING)) {
		                mSections[i] = mSections[i].trim();
		            }

		            mPositions[i] = position;
		            position += counts[i];
		        }
		        mCount = position;
			} else {
				Log.d(TAG, "not contain EXTRA_ADDRESS_BOOK_INDEX_TITLES");
				mSections = null;
				mPositions = null;
			}
		}
		
		Log.d(TAG,  "[changeCursor] getCount = " + mCursor.getCount());
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		if (mCursor==null || mCursor.isClosed()) {
			return 0;
		}
		
		return mCursor.getCount();
	}
	
	public void setQueryString(String queryString) {
		mQueryString = queryString;
		
		if (TextUtils.isEmpty(mQueryString)) {
			mSearchMode = false;
		} else {
			mSearchMode = true;
		}
	}
	
	public String getQueryString() {
		return mQueryString;
	}
	
	public boolean isSearchMode() {
		return mSearchMode;
	}
	
	public void configureLoader(CursorLoader loader) {
		String query = getQueryString();
		
		if (query == null) {
			query = "";
		}
		
		query = query.trim();
		
		if (TextUtils.isEmpty(mQueryString)) {
			Uri uri= Contacts.CONTENT_URI;
			uri = uri.buildUpon()
				.appendQueryParameter(Contacts.EXTRA_ADDRESS_BOOK_INDEX, "true").build();
			loader.setUri(uri);
			loader.setProjection(CONTACTS_PROJECTION);
		} else {
			Builder builder = Contacts.CONTENT_FILTER_URI.buildUpon();
			builder.appendPath(query);
			builder.appendQueryParameter(ContactsContract.DIRECTORY_PARAM_KEY,
                    String.valueOf(Directory.DEFAULT));
			builder.appendQueryParameter("snippet_args", SNIPPET_ARGS);
            builder.appendQueryParameter("deferred_snippeting","1");
            builder.appendQueryParameter(Contacts.EXTRA_ADDRESS_BOOK_INDEX, "true");
            loader.setUri(builder.build());
            loader.setProjection(FILTER_PROJECTION);
		}
		
		loader.setSortOrder(Contacts.SORT_KEY_PRIMARY);
	}

	@Override
	public Object getItem(int position) {
		if (position <0 || !mCursor.moveToPosition(position)) {
			return null;
		}
		
		String lookupKey = mCursor.getString(LOOKUP_KEY);
		long contactId = mCursor.getLong(CONTACT_ID);
		
		Uri uri = Contacts.getLookupUri(contactId, lookupKey);

		return uri;
	}

	@Override
	public long getItemId(int position) {
		
		if (position <0 || !mCursor.moveToPosition(position)) {
			return -1;
		}
		
		long contactId = mCursor.getLong(CONTACT_ID);
		return contactId;
	}

	private boolean isLastInSection(int position) {
		if (position < 0 || position >= mCursor.getCount()-1) {
			return true;
		}

		if (mPositions != null) {
			int length = mPositions.length;
			for (int i=0; i<length; i++) {
				if(position == mPositions[i] - 1) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.contact_list_item_view, null);
		}
		
		TextView displayNameView = (TextView)convertView.findViewById(R.id.display_name);
		
		mCursor.moveToPosition(position);
		String displayName = mCursor.getString(DISPLAY_NAME);
		displayNameView.setText(displayName);
		
		bindSectionHeader(convertView, position);
		
		if (mSearchMode) {
			bindSnippetView(convertView, position, mCursor);
		} else {
			TextView snippetView = (TextView)convertView.findViewById(R.id.snippet_view);
			snippetView.setVisibility(View.GONE);
		}

		View dividerView = convertView.findViewById(R.id.item_divider);
		if (isLastInSection(position)) {
			dividerView.setVisibility(View.GONE);
		} else {
			dividerView.setVisibility(View.VISIBLE);
		}
		
		return convertView;
	}
	
	private void bindSnippetView(View convertView, int position, Cursor cursor) {
		String snippet = cursor.getString(SNIPPET);
		
		TextView snippetView = (TextView)convertView.findViewById(R.id.snippet_view);
		if(!TextUtils.isEmpty(snippet)){
			snippetView.setText(snippet);
			snippetView.setVisibility(View.VISIBLE);
		} else {
			snippetView.setVisibility(View.GONE);
		}
	}
	
	/**
	 * 每组第一个item显示字母头部
	 * */
	protected void bindSectionHeader(View convertView, int position) {
		Placement placement = getItemPlacementInSection(position);
		
		String sectionHeader = placement.sectionHeader;
		
		TextView headerTextView = (TextView)convertView.findViewById(R.id.item_header);
		if (TextUtils.isEmpty(sectionHeader)) {
			headerTextView.setVisibility(View.GONE);
		} else {
			headerTextView.setText(sectionHeader);
			headerTextView.setVisibility(View.VISIBLE);
		}
	}
	
	public Placement getItemPlacementInSection(int position) { 
		if (mPlacementCache.position == position) {
            return mPlacementCache;
        }
		
		mPlacementCache.position = position;
		
		int section = getSectionForPosition(position);
        if (section != -1 && getPositionForSection(section) == position) {
            mPlacementCache.firstInSection = true;
            mPlacementCache.sectionHeader = (String)mSections[section];
        } else {
            mPlacementCache.firstInSection = false;
            mPlacementCache.sectionHeader = null;
        }

        mPlacementCache.lastInSection = (getPositionForSection(section + 1) - 1 == position);
        
        return mPlacementCache;
	}
	
	public int getSectionForPosition(int position) {
        if (mSections == null || mPositions == null) {
            return -1;
        }

        if (position < 0 || position >= mCount) {
            return -1;
        }

        int index = Arrays.binarySearch(mPositions, position);

        return index;
    }
	
	public int getPositionForSection(int sectionIndex) {
        if (mSections == null || mPositions == null) {
            return -1;
        }

        if (sectionIndex < 0 || sectionIndex >= mSections.length) {
            return -1;
        }

        return mPositions[sectionIndex];
    }
	
	public static final class Placement {
        private int position = ListView.INVALID_POSITION;
        public boolean firstInSection;
        public boolean lastInSection;
        public String sectionHeader;

        public void invalidate() {
            position = ListView.INVALID_POSITION;
        }
    }

}
