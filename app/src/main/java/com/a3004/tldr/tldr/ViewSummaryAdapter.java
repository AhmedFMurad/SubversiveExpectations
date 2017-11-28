package com.a3004.tldr.tldr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jeffrey on 11-27-2017.
 */

public class ViewSummaryAdapter extends ArrayAdapter<Summary> {
    Context mContext;
    private ArrayList<Summary> summaries;

    private static class ViewHolder {
        ImageButton upvote;
        ImageButton downvote;
        TextView summary;
        TextView username;
    }

    public ViewSummaryAdapter(ArrayList<Summary> summaries, Context context){
        super (context, R.layout.summary_item, summaries);
        this.summaries = summaries;
        this.mContext = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        Summary currSummary = summaries.get(position);
        ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.summary_item, parent, false);
            viewHolder.upvote = convertView.findViewById(R.id.upvote_button);
            viewHolder.downvote = convertView.findViewById(R.id.downvote_button);
            viewHolder.summary = convertView.findViewById(R.id.summary);
            viewHolder.username = convertView.findViewById(R.id.username);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.summary.setText(currSummary.getContent());

        return convertView;
    }
}
