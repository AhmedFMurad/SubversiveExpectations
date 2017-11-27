package com.a3004.tldr.tldr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prof.rssparser.Article;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Jeffrey on 11-09-2017.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {
    Context mContext;
    private ArrayList<Article> articles;
    public String url;
    public static final String ARTICLE_URL = "url";

    private static class ViewHolder {
        TextView title;
        TextView content;
        ImageView image;
        ImageButton link;
        ImageButton summary;
    }


    public ArticleAdapter(ArrayList<Article> articles, Context context){
        super (context, R.layout.item2, articles);
        this.articles = articles;
        this.mContext = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        Article currArticle = articles.get(position);
        ViewHolder viewHolder;


        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item2, parent, false);
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.content = convertView.findViewById(R.id.content);
            viewHolder.image = convertView.findViewById(R.id.image_view);
            viewHolder.link = convertView.findViewById(R.id.link_button);
            viewHolder.summary = convertView.findViewById(R.id.summary_button);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.title.setText(currArticle.getTitle());
            viewHolder.content.setText(currArticle.getDescription());
            if (currArticle.getImage() != null) {
                Picasso.with(mContext).load(currArticle.getImage()).into(viewHolder.image);
            } else {
                Picasso.with(mContext).load(R.drawable.pepe1).into(viewHolder.image);
            }
        url = currArticle.getLink();
        viewHolder.summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewSummaries.class);
                intent.putExtra("newUrl", url);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }


}
