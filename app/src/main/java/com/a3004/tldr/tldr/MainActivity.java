package com.a3004.tldr.tldr;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // added here
    private RecyclerView mRecylerView;
    private List<String> mDatas;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        // added stuff from here
        initData();

        mRecylerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));
        mRecylerView.setAdapter(mAdapter = new RecyclerViewAdapter());
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for(int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
    {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    MainActivity.this).inflate(R.layout.item_home, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mTextView.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public MyViewHolder(View view) {
                super(view);
                mTextView = (TextView) view.findViewById(R.id.text_view);
            }
        }
    }

}