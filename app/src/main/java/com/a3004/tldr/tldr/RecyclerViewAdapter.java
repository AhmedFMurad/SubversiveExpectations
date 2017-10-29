package com.a3004.tldr.tldr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<mViewHolder> {

    // --- save this for later when Article and Summary classes get completed ---

    private Context context;
    private List<Article> articles;
    private LayoutInflater mInflater;
    private View view;
    private MyItemClickListener listener;

    public RecyclerViewAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() { return articles.size(); }

    @Override
    public mViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.item, parent, false);
        mViewHolder viewHolder = new mViewHolder(view, listener);
        return viewHolder;
    }

    public void onBindViewHolder(final mViewHolder holder, final int position) {
        holder.tv.setText(articles.get(position).getArticleText());
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, position +"abc", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.listener = listener;
    }
}

class mViewHolder extends RecyclerView.ViewHolder {
    ImageView iv;
    View itemView;
    TextView tv;
    MyItemClickListener mListener;

    public mViewHolder(View itemView, final MyItemClickListener mListener) {
        super(itemView);
        this.itemView = itemView;
        iv = (ImageView) itemView.findViewById(R.id.image_view);
        tv = (TextView) itemView.findViewById(R.id.title);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, getAdapterPosition());
            }
        });
    }
}
interface MyItemClickListener {
    void onItemClick(View view, int position);
}
