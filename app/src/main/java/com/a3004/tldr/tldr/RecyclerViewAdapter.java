package com.a3004.tldr.tldr;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.mViewHolder> {

    // --- save this for later when Article and Summary classes get completed ---

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

    private List<Article> articles;

    RecyclerViewAdapter(ArrayList<Article> articles) { this.articles = articles; }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.activity_one, viewGroup, false);
        mViewHolder mvh = new mViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(mViewHolder m_view_holder, int i) {
        m_view_holder.articleCategory.setText(articles.get(i).getArticleCategory());
        m_view_holder.articleText.setText(articles.get(i).getArticleText());
        m_view_holder.articleImage.setImageResource(articles.get(i).getArticleID());
    }

    @Override
    public int getItemCount() { return articles.size(); }

}
