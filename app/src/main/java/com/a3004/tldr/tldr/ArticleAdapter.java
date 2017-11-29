package com.a3004.tldr.tldr;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.prof.rssparser.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter extends ArrayAdapter<Article> {
    Context mContext;
    private ArrayList<Article> articles;
    ViewHolder viewHolder;
    Firebase mFirebase = new Firebase(mContext);
    public String url;

    private static class ViewHolder {
        TextView title;
        TextView content;
        ImageView image;
        ImageButton link;
        ImageButton summary;
        ImageButton like;
    }


    public ArticleAdapter(ArrayList<Article> articles, Context context) {
        super(context, R.layout.item2, articles);
        this.articles = articles;
        this.mContext = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Article currArticle = articles.get(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item2, parent, false);
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.content = convertView.findViewById(R.id.content);
            viewHolder.image = convertView.findViewById(R.id.image_view);
            viewHolder.link = convertView.findViewById(R.id.link_button);
            viewHolder.summary = convertView.findViewById(R.id.summary_button);
            viewHolder.like = convertView.findViewById(R.id.like_button);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(currArticle.getTitle());
        viewHolder.content.setText(currArticle.getDescription());
        viewHolder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(mContext, Uri.parse(currArticle.getLink()));
            }
        });
        url = currArticle.getLink().replaceAll("\\.", "").replaceAll("/", "");
        viewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebase.getDatabaseReference().addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (!dataSnapshot
                                .child("categories")
                                .child("technology")
                                .child(currArticle.getLink().replaceAll("\\.", "").replaceAll("/", ""))
                                .child("likes")
                                .hasChild(mFirebase.getFirebaseAuth().getCurrentUser().getUid())) {

                            mFirebase.getDatabaseReference()
                                    .child("categories")
                                    .child("technology")
                                    .child(currArticle.getLink().replaceAll("\\.", "").replaceAll("/", ""))
                                    .child("likes")
                                    .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                                    .setValue(1);
                            viewHolder.like.setImageResource(R.drawable.ic_thumb_up_green_24dp);
                        } else {
                            mFirebase.getDatabaseReference()
                                    .child("categories")
                                    .child("technology")
                                    .child(currArticle.getLink().replaceAll("\\.", "").replaceAll("/", ""))
                                    .child("likes")
                                    .child(mFirebase.getFirebaseAuth().getCurrentUser().getUid())
                                    .removeValue();
                            viewHolder.like.setImageResource(R.drawable.ic_thumb_up_black_24dp);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        if (currArticle.getImage() != null) {
            Picasso.with(mContext).load(currArticle.getImage()).into(viewHolder.image);
        } else {
            Picasso.with(mContext).load(R.drawable.pepe1).into(viewHolder.image);
        }
        viewHolder.summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewSummaries.class);
                intent.putExtra("newUrl", currArticle.getLink());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }


}
