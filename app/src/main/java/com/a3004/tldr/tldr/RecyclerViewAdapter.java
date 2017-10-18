package com.a3004.tldr.tldr;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.mViewHolder> {

    public static class mViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;
        ImageView articleImage;
        TextView articleCategory;
        TextView articleText;

        mViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView)itemView.findViewById(R.id.card_view);
            articleImage = (ImageView)itemView.findViewById(R.id.article_image);
            articleCategory = (TextView)itemView.findViewById(R.id.title);
            articleText = (TextView)itemView.findViewById(R.id.content);
        }
    }

    List<Article> articles;

    RecyclerViewAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_home, viewGroup, false);
        mViewHolder mvh = new mViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(mViewHolder m_view_holder, int i) {
        m_view_holder.articleCategory.setText(articles.get(i).articleCategory);
        m_view_holder.articleText.setText(articles.get(i).articleText);
        m_view_holder.articleImage.setImageResource(articles.get(i).articleID);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
