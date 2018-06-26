package com.example.android.newsappudacity;



import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>, SwipeRefreshLayout.OnRefreshListener {
    /** Tag for the log messages */

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private NewsAdapter mAdapter;

    private static final int NEWS_LOADER_ID = 0;


    /** URL to query the news */
    public static final String News_URL =
            "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2018-01-01&show-tags=contributor&api-key=ff683276-7a94-435d-800e-659cd916e432";

    ListView mListView;
    TextView mTextView;
    SwipeRefreshLayout swipe;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       swipe = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
       swipe.setOnRefreshListener(this);


       mListView = findViewById(R.id.list);
       mAdapter = new NewsAdapter(this,new ArrayList<News>());
       mListView.setAdapter(mAdapter);

       mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               News currentNews = mAdapter.getItem(position);
               String url = currentNews.getWebUrl();
               Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse(url));
               startActivity(intent);
           }
       });

       ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
       NetworkInfo networkInfo = null;
       if (manager != null) {
           networkInfo = manager.getActiveNetworkInfo();
       }

      mTextView = findViewById(R.id.text_view);

       if (networkInfo != null && networkInfo.isConnected()) {
           LoaderManager loaderManager = getSupportLoaderManager();
           loaderManager.initLoader(NEWS_LOADER_ID, null, this);

       } else {
           mTextView.setVisibility(View.VISIBLE);
           mTextView.setText(R.string.no_internet);
       }

   }

    @Override
    public android.support.v4.content.Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader (this, News_URL);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<News>> loader, List<News> data) {

        swipe.setRefreshing(false);
        if (data != null) {
            mAdapter.setNotifyOnChange(false);
            mAdapter.clear();
            mAdapter.setNotifyOnChange(true);
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<News>> loader) {
    }

    @Override
    public void onRefresh() {
        getSupportLoaderManager().restartLoader(NEWS_LOADER_ID, null, this);
    }

}



