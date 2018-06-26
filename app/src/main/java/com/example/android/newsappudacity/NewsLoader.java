package com.example.android.newsappudacity;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;


public class NewsLoader extends AsyncTaskLoader<List<News>> {

    public static final String LOG_TAG = NewsLoader.class.getSimpleName();

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        Log.d(LOG_TAG, String.valueOf(R.string.loadInBackground));
        if (mUrl == null) {
            return null;
        }

        List<News> newsList = QueryUtils.fetchNewsData(mUrl);
        if (newsList != null && !newsList.isEmpty()) {
            Log.d(LOG_TAG, String.valueOf(R.string.fetchNewsData));
        } else {
            Log.d(LOG_TAG, newsList.get(0).getTitle());
        }
        return newsList;
    }
}