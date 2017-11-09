package com.a3004.tldr.tldr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.prof.rssparser.Article;

import java.util.ArrayList;

/**
 * Created by Jeffrey on 11-09-2017.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {
    Context mContext;
    private ArrayList<Article> articles;

    private static class ViewHolder {
        TextView title;
        TextView content;
        ImageView image;
    }


    public ArticleAdapter(ArrayList<Article> articles, Context context){
        super (context, R.layout.item2, articles);
        this.articles = articles;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Article currArticle = articles.get(position);
        ViewHolder viewHolder;


        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item2, parent, false);
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.content = convertView.findViewById(R.id.content);
            viewHolder.image = convertView.findViewById(R.id.image);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(currArticle.getTitle());
        viewHolder.content.setText(currArticle.getDescription());

        return convertView;
    }
}
