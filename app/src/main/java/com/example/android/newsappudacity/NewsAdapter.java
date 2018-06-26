package com.example.android.newsappudacity;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, -1, new ArrayList<News>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView
                    = LayoutInflater.from(getContext()).inflate(R.layout.news_list, parent, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView author = (TextView) convertView.findViewById(R.id.Author);
        TextView date = (TextView) convertView.findViewById(R.id.Date);
        TextView section = (TextView) convertView.findViewById(R.id.section);

        News currentNews = getItem(position);
        title.setText(currentNews.getTitle());
        author.setText(currentNews.getAuthor());
        date.setText(currentNews.getDate());
        section.setText(currentNews.getsectionName());

        return convertView;
    }
}
